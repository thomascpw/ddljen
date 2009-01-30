package org.ddljen;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

public class DDLJenTask extends Task {

	private List fileSets = new ArrayList();
	private String database = null;
	private String version = null;
	private boolean drop = false;
	private File destFile = null;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List getFileSets() {
		return fileSets;
	}

	public void setFileSets(List fileSets) {
		this.fileSets = fileSets;
	}

	public void addFileSet(FileSet fs) {
		fileSets.add(fs);
	}
	
	public File getDestFile() {
		return destFile;
	}

	public void setDestFile(File destFile) {
		this.destFile = destFile;
	}

	public boolean mustDrop() {
		return drop;
	}

	public void setDrop(boolean drop) {
		this.drop = drop;
	}

	public void execute() throws BuildException {
		for (Iterator i = fileSets.iterator(); i.hasNext();) {
			FileSet fs = (FileSet) i.next();
			List files = getFiles(fs);
			for (Iterator j = files.iterator(); j.hasNext();) {
				File schemaFile = (File) j.next();
			    try {
					DDLJen.run(schemaFile, getDatabase(), getVersion(), mustDrop(), getDestFile());
				} catch (DDLJenException e) {
					throw new BuildException(e);
				}				
			}
		}
	}

	private List getFiles(FileSet fs) {
		List files = new ArrayList();
		DirectoryScanner ds = fs.getDirectoryScanner(getProject()); 
		String[] includedFiles = ds.getIncludedFiles();
		for(int j=0; j<includedFiles.length; j++) {
		    String filename = includedFiles[j].replace('\\','/'); 
		    File base  = ds.getBasedir();
		    File file = new File(base, filename);
		    files.add(file);
		}
		return files;
	}
}
