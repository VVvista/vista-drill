package com.vista.spark.sparksql

import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/8
  */
object DailySale {
  def main(args: Array[String]): Unit = {
    // 首先还是创建SparkConf
    val conf = new SparkConf().setAppName("DailySaleScala").setMaster("local")
    // 创建SparkContext
    val sparkContext = new SparkContext(conf)
    val sqlContext = new SQLContext(sparkContext)

    // 这里着重说明一下！！！
    // 要使用Spark SQL的内置函数，就必须在这里导入SQLContext下的隐式转换
    import sqlContext.implicits._

    // 构造用户访问日志数据，并创建DataFrame
    // 模拟用户访问日志，日志用逗号隔开，第一列是日期，第二列是用户id
    val userSaleLog = Array("2018-12-30,1122,112.33", "2018-12-30,1122,112.23", "2018-12-30,1123,663",
      "2018-12-30,1124,55.55", "2018-12-30,1125,44.44", "2018-12-30,1126,33.33", "2018-12-31,1126,69",
      "2018-12-31,1127,66.66", "2018-12-31,1128,77.77", "2018-12-31,1129,88.88", "2018-12-31,1130,99.99",
      "2018-12-31,1131,201.22", "2018-12-31,1132,100.1")

    // 将模拟出来的用户访问日志RDD，转换为DataFrame
    // 首先，将普通的RDD，转换为元素为Row的RDD
    val userSaleLogRDD = sparkContext.parallelize(userSaleLog, 5)
    val userFilter = userSaleLogRDD.filter(s => if (s.split(",").length == 3) true else false)
    val userSaleLogRowRDD = userFilter.map(s => Row(s.split(",")(0), s.split(",")(2).toDouble))

    val structType = StructType(Array(StructField("data", StringType, true), StructField("sale_amount", DoubleType, true)))

    val df = sqlContext.createDataFrame(userSaleLogRowRDD, structType)

    import org.apache.spark.sql.functions._

    df.groupBy("data")
      .agg('data, sum("sale_amount")) //注意格式，data前面是单引号
      .foreach(row => println(row)) //注意结果是3列
  }
}
