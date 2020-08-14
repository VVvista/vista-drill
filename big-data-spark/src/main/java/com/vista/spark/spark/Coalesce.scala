package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/24
  */
object Coalesce {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    val rdd1 = sc.parallelize(Seq(1, 2, 3, 3))// 默认2个分区
   rdd1.coalesce(1).collect
   rdd1.coalesce(4).collect()//分区仍是2个
  }
}
