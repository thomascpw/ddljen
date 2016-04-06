The following is an un-structured, un-prioritized and incomplete list of things that will need to be done to improve ddljen.

Use this list as a starting point if you plan to contribute to ddljen. Wether you are a Java developer, tester, user, or if you just know how to improve the doc and this site, contact the project owners and they will guide you through this task.

# Roadmap #
  * publish a first alpha release
    1. provide a nice but simple example of ddljen xml schema representation (using java petstore as an example? and/or sqlserver northwind/adventureworks sample databases) - done
    1. test generated sql with oracle and mysql - done for mysql
    1. add parameters to ant build scripts (file name, dialect)
    1. write getting started guide
  * migrate to java 5?? or keep java 1.4 compatibility
  * replace System.out.println by logging
  * Use MySQL sakila sample database as an example of how to use ddljen
  * add more log messages (input file, target database, number of schema objects?, etc.)
  * check mapping of numeric types (number, integer, numeric, etc.)
  * add support for indexes
  * improve error handling (invalid/missing arguments, etc.)
  * define XML schema for ddljen
  * enforce XML schema validation
  * add support for maven
  * add support for other databases (sql server, db2, javadb/derby, hsql)
  * add support for different versions of the same database
  * add javadoc
  * upgrade libraries usd by ddljen to their latest versions
  * define testing strategy and write tests
  * write user guide (how to use ddljen, how to use ddljen's ant tasks, etc.)
  * write developer's guide (how to extend ddljen, how to support other databases, etc.)
  * write getting started guide (pre-requisites, define schema, run ddljen)
  * enrich project description on google code
  * reverse engineer a database schema into ddljen's xml file
  * configure log4j so that it creates a log directory for velocity logs, etc. instead of polluting the main directory
  * create windows/linux shell scripts to launch ddljen from the command-line
  * use precision/scale for numeric datatypes instead of size/precision
  * add a documentation section about "why ddljen": ansi sql not portable for ddl, sequences needed for oracle but not elsewhere, 1 schema definition independant of the target db, etc.