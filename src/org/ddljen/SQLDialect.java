package org.ddljen;

public class SQLDialect {

	public final static SQLDialect ORACLE = new SQLDialect("oracle", null);
	public final static SQLDialect MYSQL = new SQLDialect("mysql", null);
	
	private String name = null;
	private String version = null;
	
	public SQLDialect() {
	}

	public SQLDialect(String name, String version) {
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	
}
