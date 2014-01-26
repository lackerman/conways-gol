#!/bin/bash

# JAVA_HOME must be set to the JDK or JRE installation directory
# For example: /usr/local/jdk1.4 or /usr/local/jre1.4
if [ -z "\$JAVA_HOME" ]
then
    echo "The JAVA_HOME environment variable is not set!"
    exit
fi

MVN_HOME=`which mvn`
if [ ! $MVN_HOME ]; then
   echo -e "\a****ERROR Failed to determine Maven installation"; read; exit 1
fi

# Clean and package the project
mvn clean
mvn package

# Run the Application
$JAVA_HOME/bin/java -jar "target/conways-gol-1.0-SNAPSHOT.jar" numIterations=100 numCols=20 numRows=20
