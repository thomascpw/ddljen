package org.ddljen;

import java.io.InputStream;
import java.util.Properties;

public class TypeMapperFactory {

	public static TypeMapper createTypeMapper(SQLDialect dialect) throws DDLJenException {
		TypeMapper typeMapper = null;
		try {
			String propertyFile = dialect.getName();
			propertyFile += "/typeMap.properties";
			Properties types = new Properties();
			InputStream is = TypeMapperFactory.class.getResourceAsStream(propertyFile);
			types.load(is);
			try {
				String className = "org.ddljen." + dialect.getName() + ".TypeMapper";
				Class clazz = TypeMapperFactory.class.getClassLoader().loadClass(className);
				typeMapper = (TypeMapper) clazz.newInstance();
			} catch (Throwable e) {
				System.out.println("Could not instanciate type mapper for " + dialect.getName() + ". Using generic type mapper.");
				typeMapper = new BaseTypeMapper();				
			}
			typeMapper.setTypeMap(types);
		} catch (Exception e) {
			throw new DDLJenException(e);
		}
		return typeMapper; 
	}
	
	protected TypeMapperFactory() {
	}
}
