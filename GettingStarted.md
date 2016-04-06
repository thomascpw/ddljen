Todo

### Pre-requisites ###
  * Java 5+
  * Ant 1.6.5+


### Installing ddljen ###

  1. Download the latest version of ddljen.
  1. Unzip the distribution archive (ddljen-x.x.zip) to the directory you wish to install ddljen. The subdirectory ddljen-x.x will be created from the archive. These instructions will refer to this directory as DDLJEN\_HOME.
  1. To verify that ddljen was correctly installed, type `ant` at the command line from the DDLJEN\_HOME directory. ddljen should generate the SQL file for the sample database schema that is provided (java-petstore-2.0.xml).


### Defining your database schema ###

Todo


### Running ddljen ###

Todo


#### Running ddljen from Ant ####

Todo: ddljen can be run from Ant using ddljen's Ant task. An example of how to use


#### Running ddljen from Java ####

Todo: java -jar lib/ddljen-x.x.jar -db mysql -f java-petstore-2.0.xml -o java-petstore-2.0.sql