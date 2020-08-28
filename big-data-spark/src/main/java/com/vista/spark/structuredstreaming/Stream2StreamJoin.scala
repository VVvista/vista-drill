package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession

/**
  * @author WenTingTing by 2020/8/28
  */
object Stream2StreamJoin {
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
    val impressions = spark.readStream.load()
    val clicks = spark.readStream.load()

    //  Apply watermarks on event-time columns
    val impressionsWithWatermark = impressions.withWatermark("impressionTime", "2 hours")
    val clicksWithWatermark = clicks.withWatermark("clickTime", "3 hours")

    // Join with event-time constraints
    impressionsWithWatermark.join(
      clicksWithWatermark,
      expr(
        """
          |    clickAdId = impressionAdId AND
          |    clickTime >= impressionTime AND
          |    clickTime <= impressionTime + interval 1 hour
        """.stripMargin)
    )


  }
}
