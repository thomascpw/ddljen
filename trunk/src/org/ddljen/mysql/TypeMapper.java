package org.ddljen.mysql;

import org.ddljen.BaseTypeMapper;
import org.ddljen.DataType;
import org.ddljen.Types;

public class TypeMapper extends BaseTypeMapper implements Types {

	private static final int VARCHAR_MAX_SIZE = 300;
	
	public DataType map(DataType t) {
		DataType dataType = super.map(t);
		dataType = number2integer(dataType);
		dataType = varchar2text(dataType);
		dataType = removeTimestampSize(dataType);
		return dataType;
	}

	private DataType number2integer(DataType dataType) {
		if (dataType.getType().equalsIgnoreCase(NUMBER)) {
			if (!dataType.hasPrecision()) {
				dataType.setType(INTEGER);
			}
		}
		return dataType;
	}

	private DataType removeTimestampSize(DataType dataType) {
		if (dataType.getType().equalsIgnoreCase(TIMESTAMP)) {
			dataType.setSize(null);
		}
		return dataType;
	}

	private DataType varchar2text(DataType dataType) {
		if (dataType.getType().equalsIgnoreCase(VARCHAR)) {
			if (dataType.getSize().intValue() >= VARCHAR_MAX_SIZE) {
				dataType.setType(TEXT);
				dataType.setSize(null);
			}
		}
		return dataType;
	}
	
}
