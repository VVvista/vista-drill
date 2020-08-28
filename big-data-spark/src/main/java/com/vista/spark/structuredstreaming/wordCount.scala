package com.vista.spark.structuredstreaming

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * @author WenTingTing by 2020/8/21
  */
object wordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]")
      .appName("wordCount")
      .getOrCreate()
    val lines = spark.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    import spark.implicits._

    val words = lines.as[String].flatMap(_.split(" "))
    val wordsCounts = words.groupBy("value").count()

    words.explain()
    val query = wordsCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()



  }

}
