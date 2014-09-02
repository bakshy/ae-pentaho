# Find duplicates in files im.csv and cb.csv

This code accompanies project

## Compile

```
mvn compile
```

## Test

```
mvn test
```

## Build
```
mvn assembly:single
```

## Copy data into Hadoop

```
%>hadoop fs -copyFromLocal /home/hduser/im.csv /in/data1
%>hadoop fs -copyFromLocal /home/hduser/cb.csv /in/data2

## Run

%> hadoop jar /fuzzy-matching/target/fuzzy-matching-1.0-jar-with-dependencies.jar /in/data1 /in/data2 /out

```

