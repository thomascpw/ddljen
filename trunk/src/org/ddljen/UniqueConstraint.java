package org.ddljen;

import java.util.ArrayList;
import java.util.List;

public class UniqueConstraint extends AbstractSchemaObject {

	private List columnRefs = null;
	
	
	public UniqueConstraint() {
	}

	public UniqueConstraint(String name) {
		super(name);
	}
	
	public UniqueConstraint(String name, ColumnRef columnRef) {
		super(name);
		this.addColumnRef(columnRef);
	}
	public UniqueConstraint(String name, List columnRefs) {
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
