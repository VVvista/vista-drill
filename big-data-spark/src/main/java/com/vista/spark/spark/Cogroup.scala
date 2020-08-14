package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/27
  */
object Cogroup {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val rdd1 = sc.parallelize(Array(("aa", 1), ("bb", 2), ("cc", 6)))
    val rdd2 = sc.parallelize(Array(("aa", 3), ("dd", 4), ("aa", 5)))
    rdd1.cogroup(rdd2).collect()

  }
}
