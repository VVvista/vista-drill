package com.vista.spark.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * @author WenTingTing by 2020/6/8
  */
object ParquetPartitionDiscovery {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val dataFrame = sqlContext.read.option("basePath", "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/date=20200608/country=china/users.parquet")

    //  val dataFrame = sqlContext.read.parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/date=20200608/country=china/users.parquet")


    dataFrame.printSchema()
    /*
    root
     |-- name: string (nullable = true)
     |-- favorite_color: string (nullable = true)
     |-- favorite_numbers: array (nullable = true)
     |    |-- element: integer (containsNull = true)
     |-- date: integer (nullable = true)
     |-- country: string (nullable = true)

     */

    sqlContext.getConf("spark.sql.sources.partitionColumnTypeInference.enabled")

    sqlContext.setConf("spark.sql.sources.partitionColumnTypeInference.enabled", "false")
    val df = sqlContext.read.option("basePath", "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/date=20200608/country=china/users.parquet")
    df.printSchema()

    /*
    root
     |-- name: string (nullable = true)
     |-- favorite_color: string (nullable = true)
     |-- favorite_numbers: array (nullable = true)
     |    |-- element: integer (containsNull = true)
     |-- date: string (nullable = true)
     |-- country: string (nullable = true)

     */
  }

}
