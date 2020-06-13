package com.vista.spark.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * @author WenTingTing by 2020/6/8
  */
object JSONDataSource {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val students = sqlContext.read.json("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
    students.show()

    val studentInfo = Array("{\"name\":\"Leo\", \"age\":18}", "{\"name\":\"Marry\", \"age\":17}", "{\"name\":\"Jack\", \"age\":19}")
    val studentInfoRDD = sc.parallelize(studentInfo)
    val studentInfoDF = sqlContext.read.json(studentInfoRDD)

    studentInfoDF.show()

  }

}
