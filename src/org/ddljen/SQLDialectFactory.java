package org.ddljen;

public class SQLDialectFactory {

	public enum SupportedDialects { ORACLE, MYSQL };
	
	public static SQLDialect createDialect(String dialectName) throws UnsupportedDialectException {
		return createDialect(dialectName, null);
	}

	public static SQLDialect createDialect(String dialectName, String version) throws UnsupportedDialectException {
		SQLDialect dialect = null;
		if (dialectName != null) {
			if (isDialectSupported(dialectName)) {
				dialect = new SQLDialect(dialectName, version);
			} else {
				throw new UnsupportedDialectException("Database dialect " + dialectName + " is not currently supported by ddljen."); 
			}
		}
		return dialect;
	}

	private static boolean isDialectSupported(String dialectName) {
		String dialectNameInUpperCase = dialectName.toUpperCase();
		SupportedDialects[] supportedDialects = SupportedDialects.values();
		for (int i = 0; i < supportedDialects.length; i++) {
			String supportedDialectName = supportedDialects[i].name();
			if (supportedDialectName.equals(dialectNameInUpperCase)) return true;
		}
		return false;
	}
}
