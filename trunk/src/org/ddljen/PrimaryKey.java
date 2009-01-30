package org.ddljen;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKey extends AbstractSchemaObject {

	private List columnRefs = null;
	
	
	public PrimaryKey() {
		super();
	}

	public PrimaryKey(String name) {
		super(name);
	}
	
	public PrimaryKey(String name, ColumnRef columnRef) {
		super(name);
		this.addColumnRef(columnRef);
	}
	public PrimaryKey(String name, List columnRefs) {
		super(name);
		this.columnRefs = columnRefs;
	}

	public List getColumnRefs() {
		return columnRefs;
	}
	
	public void setColumnRefs(List columnRefs) {
		this.columnRefs = columnRefs;
	}
	
	public void addColumnRef(ColumnRef columnRef) {
		if (this.columnRefs == null) this.columnRefs = new ArrayList();
		columnRefs.add(columnRef);
	}
	
}
