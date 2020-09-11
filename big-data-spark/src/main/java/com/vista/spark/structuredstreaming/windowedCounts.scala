package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession

/**
  * @author WenTingTing by 2020/8/28
  */
object windowedCounts {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("windowedCounts")
      .getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val words = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    val windowedCounts = words
      .groupBy(
        window($"timstamp", "10 minutes", "5 minutes"),
        $"word")
      .count()

    words.withWatermark("timestamp", "10 minutes")
      .groupBy(
        window($"timestamp", "10 minutes", "5 minutes"),
        $"word")
      .count()


    import spark.implicits._
    import org.apache.spark.sql.functions._

/*    val words =    ... // streaming DataFrame of schema { timestamp: Timestamp, word: String }

    // Group the data by window and word and compute the count of each group
    val windowedCounts = words
      .withWatermark("timestamp", "10 minutes")
      .groupBy(
        window($"timestamp", "10 minutes", "5 minutes"),
        $"word")
      .count()*/
  }

}
