package org.ddljen;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DataType {

	private String type = null;
	private Integer size = null;
	private Integer precision = null;

	public DataType() {
	}

	public DataType(String type) {
		this.type = type;
	}
	
	public DataType(String type, Integer size) {
		this.type = type;
		this.size = size;
	}

	public DataType(String type, Integer size, Integer precision) {
		this.type = type;
		this.size = size;
		this.precision = precision;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public boolean hasPrecision() {
		return (precision != null && precision.intValue() == 0);
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
