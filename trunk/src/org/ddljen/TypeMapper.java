package org.ddljen;

import java.util.Properties;

public interface TypeMapper {

	public DataType map(DataType dataType);
	public void setTypeMap(Properties properties);
	
}