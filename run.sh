#!/bin/bash
mvn clean package
cd dataset
java -Djava.library.path=./ -cp CyEQ-1.0-SNAPSHOT-jar-wi-dependencies.jar:z3/com.microsoft.z3.jar cyeq.Main