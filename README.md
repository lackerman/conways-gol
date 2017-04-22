# Conway's Game of Life (conways-gol)

Java Implementation of Conway's Game of Life.

Always wanted to implement Conway's GOL, just never got around to it. Then an interview
came around and boom! 2 birds, 1 stone.

## Prerequisites

* Java 8
* Maven

## Clean and package the project

```
mvn clean package
```

## Run the Application

```
java -jar "target/conways-gol-1.0-SNAPSHOT.jar" numIterations=100 numCols=20 numRows=20 gui
```

or by using the provided scripts, `run.sh` for *nix or `run.bat` for windows.