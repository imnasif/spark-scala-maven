package me.nasif.sparkscala

import org.apache.spark.{SparkConf, SparkContext}

object App {
  def main(args: Array[String]) {

    if (args.length < 2) {
      System.err.println("=> wrong parameters number")
      System.err.println("Usage: MainExample <path-to-files> <output-path>")
      System.exit(1)
    }

    val jobName = "Example"
    val conf = new SparkConf().setAppName(jobName)
    val sc = new SparkContext(conf)

    val inputFile = args(0)
    val outputPath = args(1)
    println("=> jobName \"" + jobName + "\"")
    println("=> pathToFiles \"" + inputFile + "\"")

    val input =  sc.textFile(inputFile)
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
    counts.saveAsTextFile(outputPath)

  }
}
