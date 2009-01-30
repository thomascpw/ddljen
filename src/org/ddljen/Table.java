package org.ddljen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Table extends AbstractSchemaObject {

	private List columns = null;
	private PrimaryKey primaryKey = null;
	private List foreignKeys = null;
	private List uniqueConstraints = null;
	
	public Table() {
	}

	public Table(String name) {
		super(name);
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List getColumns() {
		return columns;
	}

	public void setColumns(List columns) {
		this.columns = columns;
	}
	
	public void addColumn(Column column) {
		if (columns == null) columns = new ArrayList();
		columns.add(column);
	}
	
	public List getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(List foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public void addForeignKey(ForeignKey foreignKey) {
		if (this.foreignKeys == null) this.foreignKeys = new ArrayList();
		this.foreignKeys.add(foreignKey);		
	}
	
	public List getUniqueConstraints() {
		return uniqueConstraints;
	}

	public void setUniqueConstraints(List uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

	public void addUniqueConstraint(UniqueConstraint uc) {
		if (this.uniqueConstraints == null) this.uniqueConstraints = new ArrayList();
		this.uniqueConstraints.add(uc);		
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
