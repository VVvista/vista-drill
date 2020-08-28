package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime

/**
  * @author WenTingTing by 2020/8/28
  */
object wordCount2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("wordCount")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    val word = lines.as[String].flatMap(_.split(" "))

    val wordCounts = word.groupBy("value").count()

    val query = wordCounts.writeStream
      .format("console")
      .outputMode("complete")
      .trigger(ProcessingTime.create("10 seconds"))
      .start()

    query.awaitTermination()

  }

}
