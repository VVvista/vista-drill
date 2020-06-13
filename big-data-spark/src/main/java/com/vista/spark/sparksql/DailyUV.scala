package com.vista.spark.sparksql

import org.apache.spark.sql.types.{DataTypes, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/8
  */
class DailyUV {
  def main(args: Array[String]): Unit = {
    // 首先还是创建SparkConf
    val conf = new SparkConf().setAppName("DailyUVScala").setMaster("local")
    // 创建SparkContext
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 这里着重说明一下！！！
    // 要使用Spark SQL的内置函数，就必须在这里导入SQLContext下的隐式转换
    import sqlContext.implicits._


    // 构造用户访问日志数据，并创建DataFrame
    // 模拟用户访问日志，日志用逗号隔开，第一列是日期，第二列是用户id
    val userAccessLog = Array("2018-12-30,1122", "2018-12-30,1122", "2018-12-30,1123",
      "2018-12-30,1124", "2018-12-30,1125", "2018-12-30,1126", "2018-12-31,1126",
      "2018-12-31,1127", "2018-12-31,1128", "2018-12-31,1129", "2018-12-31,1130",
      "2018-12-31,1131", "2018-12-31,1132")

    // 将模拟出来的用户访问日志RDD，转换为DataFrame
    // 首先，将普通的RDD，转换为元素为Row的RDD
    val userAccessLogRDD = sc.parallelize(userAccessLog, 5)
    val userAccessLogRowRDD = userAccessLogRDD.map(s => Row(s.split(",")(0), s.split(",")(1)))

    val structType = StructType(Array(StructField("data", DataTypes.StringType, true), StructField("userid", StringType, true)))
    val df = sqlContext.createDataFrame(userAccessLogRowRDD, structType)
    import org.apache.spark.sql.functions._
    // 聚合函数
    df.groupBy("data").agg(df("data"), countDistinct(df("userid"))) //注意格式，data前面是单引号
    df.groupBy("data").agg(df("data"), countDistinct(df("userid"))).collect.foreach(row => println(row))
    df.groupBy("data").agg('data, countDistinct('userid)).collect.foreach(row => println(row))

    //集合函数  array_contains, explode, size, sort_array

    //日期函数
    df.select(year(df("data"))).collect.foreach(row => println(row))
  }
}
