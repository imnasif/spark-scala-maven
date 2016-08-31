#!/usr/bin/env bash
mvn clean package
hadoop fs -rm -r out.txt
spark-submit --class me.nasif.sparkscala.App \
  --master yarn-cluster \
  target/spark-scala-1.0-SNAPSHOT.jar \
  in.txt \
  out