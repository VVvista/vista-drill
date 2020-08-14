package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/23
  */
object Cartesian {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    val rdd1 = sc.parallelize(List("张三", "李四", "王五"))
    val rdd2 = sc.parallelize(List(60, 70, 90))
    rdd1.cartesian(rdd2).collect()
  }
}
