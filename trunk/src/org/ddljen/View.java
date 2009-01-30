package org.ddljen;


public class View extends AbstractSchemaObject {

	private String definition = null;
	
	public View() {	
	}
	
	public View (String name) {
		super(name);
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
}
