package org.ddljen;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class SchemaReader {

	public static Schema read(File xmlSchema) throws DDLJenException {
		
		Schema schema = new Schema();
		
		try {
			
			SAXReader reader = new SAXReader();
	        	Document document = reader.read(xmlSchema);
	        	List sequenceNodes = document.selectNodes("//sequence");
	        	if (sequenceNodes != null) {
	        		Iterator i = sequenceNodes.iterator();
	        		while (i.hasNext()) {
	        			Node sequenceNode = (Node) i.next();
	        			String sequenceName = sequenceNode.valueOf("@name");
	        			Sequence sequence = new Sequence();
	        			sequence.setName(sequenceName);
	        			schema.addSequence(sequence);
	        		}
	        	}
	        	List tableNodes = document.selectNodes("//schema/table");
	        	if (tableNodes != null) {
		        	Iterator i = tableNodes.iterator();
		        	while (i.hasNext()) {
		        		Node tableNode = (Node) i.next();
		        		String name = tableNode.valueOf("@name");
		        		Table table = new Table(name);
		        		schema.addTable(table);
		        		List columns = createColumns(tableNode);
        				table.setColumns(columns);
		        		PrimaryKey primaryKey = createPrimaryKey(tableNode);
		        		table.setPrimaryKey(primaryKey);
					List foreignKeys = createForeignKeys(tableNode);
					table.setForeignKeys(foreignKeys);
					List uniqueConstraints = createUniqueConstraints(tableNode);
					table.setUniqueConstraints(uniqueConstraints);
		        	}
	        	}
        	
	        	List viewNodes = document.selectNodes("//schema/view");
	        	if (viewNodes != null) {
		        	Iterator i = viewNodes.iterator();
		        	while (i.hasNext()) {
		        		Node viewNode = (Node) i.next();
		        		String name = viewNode.valueOf("@name");
		        		View view = new View(name);
		        		String definition = viewNode.valueOf(".").trim();
		        		view.setDefinition(definition);
		        		schema.addView(view);
		        	}
	        	}

		} catch (Exception e) {
			throw new DDLJenException(e);
		}

		return schema;
	}

	private static List createColumns(Node tableNode) {
		List columns = null;
		List columnNodes = tableNode.selectNodes("column");
		if (columnNodes != null) {
			Iterator j = columnNodes.iterator();
			while (j.hasNext()) {
				Node columnNode = (Node) j.next();
				String columnName = columnNode.valueOf("@name");
				String type = columnNode.valueOf("@type");
				Integer size = toInteger(columnNode.valueOf("@size"));
				Integer precision = toInteger(columnNode.valueOf("@precision"));
				Boolean isNullable = toBoolean(columnNode.valueOf("@nullable"));
				Boolean isUnique = toBoolean(columnNode.valueOf("@unique"));
				Boolean isAutoIncreement = toBoolean(columnNode.valueOf("@autoIncrement"));
				String desc = columnNode.valueOf("@desc");
				Column column = new Column(columnName);
				DataType dataType = new DataType(type, size, precision);
				column.setDataType(dataType);
				if (isNullable != null) column.setNullable(isNullable.booleanValue());
				if (isUnique != null) column.setUnique(isUnique.booleanValue());
				if (isAutoIncreement != null) column.setAutoIncrement(isAutoIncreement.booleanValue())
;				column.setDescription(desc);
				if (columns == null) columns = new ArrayList();
				columns.add(column);
			}
		}
		return columns;
	}

	private static PrimaryKey createPrimaryKey(Node tableNode) {
		PrimaryKey primaryKey = null;
		Node primaryKeyNode = tableNode.selectSingleNode("primary-key");
		if (primaryKeyNode != null) {
			String primaryKeyName = primaryKeyNode.valueOf("@name");
			primaryKey = new PrimaryKey(primaryKeyName);
			List columnRefNodes = primaryKeyNode.selectNodes("column-ref");
			// TODO: a PK cannot be declared without column ref, throw an exception
			Iterator j = columnRefNodes.iterator();
			while (j.hasNext()) {
				Node columnRefNode = (Node) j.next();
				String columnRefName = columnRefNode.valueOf("@name");
				ColumnRef columnRef = new ColumnRef();
				columnRef.setName(columnRefName);
				primaryKey.addColumnRef(columnRef);
			}
		}
		
		return primaryKey;
	}

	private static List createForeignKeys(Node tableNode) {
		List foreignKeys = null;
		
		List foreignKeyNodes = tableNode.selectNodes("foreign-key");
		if (foreignKeyNodes != null) {
			Iterator i = foreignKeyNodes.iterator();
			while (i.hasNext()) {
				Node foreignKeyNode = (Node) i.next();
				String foreignKeyName = foreignKeyNode.valueOf("@name");
				ForeignKey foreignKey = new ForeignKey();
				foreignKey.setName(foreignKeyName);
				Node localColumnRefNode = foreignKeyNode.selectSingleNode("local-column-ref"); 
				// TODO: a FK cannot be declared without local and foreign column ref, throw an exception
				String localColumnRefName = localColumnRefNode.valueOf("@name");
				ColumnRef columnRef = new ColumnRef();
				columnRef.setName(localColumnRefName);
				foreignKey.setLocalColumnRef(columnRef);
				Node foreignColumnRefNode = foreignKeyNode.selectSingleNode("foreign-column-ref");
				String foreignTableName = foreignColumnRefNode.valueOf("@table"); 
				String foreignColumnRefName = foreignColumnRefNode.valueOf("@name");
				ColumnRef foreignColumnRef = new ColumnRef();
				foreignColumnRef.setName(foreignColumnRefName);
				foreignColumnRef.setTable(foreignTableName);
				foreignKey.setForeignColumnRef(foreignColumnRef);
				Boolean onDeleteCascade = toBoolean(foreignKeyNode.valueOf("@onDeleteCascade"));
				foreignKey.setOnDeleteCascade(onDeleteCascade);
				if (foreignKeys == null) foreignKeys = new ArrayList();
				foreignKeys.add(foreignKey);
			}
		}
			
		return foreignKeys;
	}

	private static List createUniqueConstraints(Node tableNode) {
		List uniqueConstraints = null;
		
		List uniqueContraintNodes = tableNode.selectNodes("unique-constraint");
		if (uniqueContraintNodes != null) {
			Iterator i = uniqueContraintNodes.iterator();
			while (i.hasNext()) {
				Node uniqueContraintNode = (Node) i.next();
				String contraintName = uniqueContraintNode.valueOf("@name");
				UniqueConstraint uniqueConstraint = new UniqueConstraint();
				uniqueConstraint.setName(contraintName);
				List columnRefNodes = uniqueContraintNode.selectNodes("column-ref");
				// TODO: a unique constraint cannot be declared without column ref, throw an exception
				Iterator j = columnRefNodes.iterator();
				while (j.hasNext()) {
					Node columnRefNode = (Node) j.next();
					String columnRefName = columnRefNode.valueOf("@name");
					ColumnRef columnRef = new ColumnRef();
					columnRef.setName(columnRefName);
					uniqueConstraint.addColumnRef(columnRef);
				}
				// TODO: avoid this and set list to null on return instead
				if (uniqueConstraints == null) uniqueConstraints = new ArrayList();
				uniqueConstraints.add(uniqueConstraint);
			}
		}
			
		return uniqueConstraints;
	}

	private static Integer toInteger(String s) {
		Integer i = null;
		try {
			i = new Integer(s);
		} catch (NumberFormatException e) {
		}
		return i;
	}

	private static Boolean toBoolean(String s) {
		Boolean b = null;
		if (s != null && s.trim().equals("") == false) {
			if (s.trim().equalsIgnoreCase("true")) b = Boolean.TRUE;
			else b = Boolean.FALSE;
		}
		return b;
	}
}
