package org.ddljen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class DDLJen {

	private static final String DEFAULT_SOURCE_FILE_EXTENSION = ".xml";

	private static final String DEFAULT_DEST_FILE_EXTENSION = ".sql";

	/**
	 * @param args
	 * @throws DDLJenException 
	 */
	public static void main(String[] args) throws Exception {
		DDLJen ddljen = new DDLJen();
		DDLJenOptions options = ddljen.parseCommandLine(args);
		DDLJen.run(options.getSchemaFile(), options.getDialect(), options.mustDrop(), options.getDestFile());
	}

	public static void run(File schemaFile, SQLDialect dialect) throws DDLJenException {
		DDLJen.run(schemaFile, dialect, false, null);	
	}

	public static void run(String schemaFile, SQLDialect dialect) throws DDLJenException {
		File s = new File(schemaFile);
		DDLJen.run(s, dialect, false, null);	
	}

	public static void run(File schemaFile, SQLDialect dialect, boolean drop) throws DDLJenException {
		DDLJen.run(schemaFile, dialect, drop, null);	
	}

	public static void run(String schemaFile, SQLDialect dialect, boolean drop) throws DDLJenException {
		File s = new File(schemaFile);
		DDLJen.run(s, dialect, drop, null);	
	}

	public static void run(String schemaFile, SQLDialect dialect, boolean drop, String destFile) throws DDLJenException {
		File s = new File(schemaFile);
		File d = null;
		if (destFile != null) d = new File(destFile);
		DDLJen.run(s, dialect, drop, d);	
	}

	public static void run(File schemaFile, SQLDialect dialect, boolean drop, File destFile) throws DDLJenException {
		DDLJen ddljen = new DDLJen();
		Schema s = ddljen.readSchema(schemaFile);
		ddljen.mapTypes(s, dialect);
		if (destFile == null) {
			destFile = new File(
					replaceFileExtension(schemaFile, DEFAULT_SOURCE_FILE_EXTENSION, DEFAULT_DEST_FILE_EXTENSION));
		}
		ddljen.generateSQL(s, dialect, drop, destFile);
	}

	private static String replaceFileExtension(File schemaFile, String extensionToReplace, String newExtension) {
		String name;
		try {
			name = schemaFile.getCanonicalPath();
		} catch (IOException e) {
			name = schemaFile.getName();
		}
		if (name.endsWith(extensionToReplace)) name = name.substring(0, name.length() - extensionToReplace.length());
		name = name + newExtension;
		return name;
	}

	private DDLJenOptions parseCommandLine(String[] args) {
		Options options = new Options();
		addOption(options, "f", "file", "ddljen xml file", "file", true);
		addOption(options, "db", "database", "database", "database", true);
		addOption(options, "v", "version", "database version", "version", false);
		addOption(options, "o", "output", "output file", "file", false);
		addOption(options, "drop", "drop", "drop tables before creating them", null, false);
		
		DDLJenOptions ddljenOptions = new DDLJenOptions();
		try {
			CommandLineParser commandLineParser = new GnuParser();
			CommandLine commandLine = commandLineParser.parse(options, args);
			if (commandLine.hasOption("f")) {
				ddljenOptions.setSchemaFile(new File(commandLine.getOptionValue("f")));
			}
			if (commandLine.hasOption("db")) {
				String dialectValue = commandLine.getOptionValue("db");
				String versionValue = null;
				if (commandLine.hasOption("v")) {
					versionValue = commandLine.getOptionValue("v");
				}
				SQLDialect dialect = SQLDialectFactory.createDialect(dialectValue, versionValue);
				ddljenOptions.setDialect(dialect);
			}
			if (commandLine.hasOption("o")) {
				ddljenOptions.setDestFile(new File(commandLine.getOptionValue("o")));
			}
			if (commandLine.hasOption("drop")) {
				ddljenOptions.setDrop(true);
			}
		} catch( Exception e ) {
			// TODO: print clear error message
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "ddljen", options );
			System.exit(1);
		}
		return ddljenOptions;
	}

	private Option addOption(Options options, String opt, String longOpt, String desc, String argName, boolean isRequired) {
		boolean hasArg = argName != null && argName.trim().length() > 0;
		Option o = new Option(opt, longOpt, hasArg, desc);
		o.setRequired(isRequired);
		options.addOption(o);
		return o;
	}

	private Schema readSchema(File file) throws DDLJenException {
		Schema s = SchemaReader.read(file);
		//System.out.println("Schema: " + s.toString());
		return s;
	}

	private void generateSQL(Schema s, SQLDialect dialect, boolean drop, File destFile) throws DDLJenException {
		FileWriter writer;
		try {
			writer = new FileWriter(destFile);
			SQLGenerator generator = new SQLGenerator();
		    generator.generate(s, dialect, drop, writer);
		    System.out.println("SQL schema generated at " + destFile.getAbsolutePath());
		} catch (IOException e) {
			throw new DDLJenException(e);
		}
	    try {
			writer.close();
		} catch (IOException e) {
			// safe to ignore exceptions on close
		}
	}

	private void mapTypes(Schema s, SQLDialect dialect) throws DDLJenException {
		TypeMapper typeMapper = TypeMapperFactory.createTypeMapper(dialect);
		if (s.getTables() != null) {
			for (Iterator i = s.getTables().iterator(); i.hasNext();) {
				Table table = (Table) i.next();
				if (table.getColumns() != null) {
					for (Iterator j = table.getColumns().iterator(); j.hasNext();) {
						Column column = (Column) j.next();
						DataType dataType = column.getDataType();
						dataType = typeMapper.map(dataType);
						column.setDataType(dataType);
					}
				}
			}
		}
	}

	private class DDLJenOptions {
		private File schemaFile = null;
		private SQLDialect dialect = null;
		private File destFile = null;
		private boolean drop = false;

		public File getSchemaFile() {
			return schemaFile;
		}
		public void setSchemaFile(File schemaFile) {
			this.schemaFile = schemaFile;
		}
		public SQLDialect getDialect() {
			return dialect;
		}
		public void setDialect(SQLDialect dialect) {
			this.dialect = dialect;
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
	}
}
