package org.ddljen;


public class Sequence extends AbstractSchemaObject {

	private Integer initialValue = null;
	private Integer incrementBy = null;
	private Boolean isCycling = null;
	
	public Integer getIncrementBy() {
		return incrementBy;
	}
	
	public void setIncrementBy(Integer incrementBy) {
		this.incrementBy = incrementBy;
	}
	
	public Integer getInitialValue() {
		return initialValue;
	}
	
	public void setInitialValue(Integer initialValue) {
		this.initialValue = initialValue;
	}
	
	public Boolean isCycling() {
		return isCycling;
	}

	public void setCycling(Boolean isCycling) {
		this.isCycling = isCycling;
	}

}
