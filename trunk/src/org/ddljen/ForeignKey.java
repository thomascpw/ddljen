package org.ddljen;


public class ForeignKey extends AbstractSchemaObject {

	private ColumnRef localColumnRef = null;
	private ColumnRef foreignColumnRef = null;
	private Boolean onDeleteCascade = Boolean.FALSE;

	public ColumnRef getForeignColumnRef() {
		return foreignColumnRef;
	}

	public void setForeignColumnRef(ColumnRef foreignColumnRef) {
		this.foreignColumnRef = foreignColumnRef;
	}

	public ColumnRef getLocalColumnRef() {
		return localColumnRef;
	}

	public void setLocalColumnRef(ColumnRef localColumnRef) {
		this.localColumnRef = localColumnRef;
	}
	
	public Boolean hasOnDeleteCascade() {
		return onDeleteCascade;
	}

	public void setOnDeleteCascade(Boolean onDeleteCascade) {
		this.onDeleteCascade = onDeleteCascade;
	}

}
