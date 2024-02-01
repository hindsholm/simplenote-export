# SimpleNote Export Utility

This program generates markdown files with meaningful names and timestamps from a Simplenote export.

Usage:

1. Export your [Simplenote](https://simplenote.com/) notes to a .json file
2. Generate valid .md files from the notes:

## Building and running

Build:

    mvn clean compile assembly:single 

Run:

    java -jar target/sn2dendron-1.0-SNAPSHOT-jar-with-dependencies.jar

Build and run all at once:

    mvn compile exec:java

## Links

- [Running a Java Maven Project from the Command Line(CLI) - With Example Code](https://www.sohamkamani.com/java/cli-app-with-maven/)
