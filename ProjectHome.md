# Introduction #

ddljen is a SQL DDL (Data Definition Language) generator.
ddljen generates SQL code for different databases from a user-defined database schema expressed in XML.
ddljen is developed in Java and can be easily integrated in Java-based projects.

# Features #
  * java-based, easy to integrate in your own java projects
  * able to generate SQL for Oracle and/or MySQL
  * easily extensible to support other RDBMS
  * supports most schema objects:
    * primary keys and foreign keys with named constraints,
    * unique and not-null constraints,
    * views,
    * sequences (ignored if they are not supported by the target database),
    * auto-increment columns (ignored if not supported by the target database),
    * etc.

# Get involved! #

Feel like you want to improve this project, add some new features, etc. Please join and help. There's enough to do, for both technical and non-technical persons. Consult the [roadmap](RoadMap.md) to get a feel how what needs to be done, or feel free to suggest other changes and improvements.


# Related/Similar projects #
  * [XML to DDL](http://xml2ddl.berlios.de/)