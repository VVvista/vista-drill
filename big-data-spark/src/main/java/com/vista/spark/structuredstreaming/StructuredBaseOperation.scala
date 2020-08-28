package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession
import org.joda.time.DateTime

/**
  * @author WenTingTing by 2020/8/28
  */
object StructuredBaseOperation {

  case class DeviceData(device: String, device_type: String, signal: Double, time: DateTime)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("StructuredBaseOperation")
      .getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._
    import org.apache.spark.sql.expressions.scalalang.typed._
    val df = spark.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()
    val ds = df.as[DeviceData]
    df.select("device").where("signal >10")
    ds.filter(_.signal > 10).map(_.device)

    df.groupBy("device_type").agg( avg("signal"))

    ds.groupByKey(_.device_type).agg(avg(_.signal))

  }
}
