package org.ddljen;

import java.util.ArrayList;
import java.util.List;

public class Schema extends AbstractSchemaObject {

	private List tables = null;
	private List sequences = null;
	private List views = null;
	
	public List getTables() {
		return tables;
	}

	public void setTables(List tables) {
		this.tables = tables;
	}
	
	public void addTable(SchemaObject table) {
		if (this.tables == null) this.tables = new ArrayList();
		this.tables.add(table);
	}

	public List getSequences() {
		return sequences;
	}

	public void setSequences(List sequences) {
		this.sequences = sequences;
	}

	public void addSequence(Sequence s) {
		if (this.sequences == null) this.sequences = new ArrayList();
		this.sequences.add(s);
	}

	public List getViews() {
		return views;
	}

	public void setViews(List views) {
		this.views = views;
	}

	public void addView(View view) {
		if (this.views == null) this.views = new ArrayList();
		this.views.add(view);
		
	}
	
}
