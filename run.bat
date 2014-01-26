@ECHO OFF

rem Clean and Package
call mvn clean
call mvn package

%JAVA_HOME%\bin\java -jar "target/conways-gol-1.0-SNAPSHOT.jar" numIterations=100 numCols=20 numRows=20

pause
