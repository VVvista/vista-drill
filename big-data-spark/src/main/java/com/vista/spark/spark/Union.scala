package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/22
  */
object Union {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val rdd1 = sc.parallelize(Seq(1, 2, 3))
    val rdd2 = sc.parallelize(Seq(3, 4, 5))

    rdd1.union(rdd2).collect() // 1, 2, 3, 3, 4, 5
    rdd1.union(rdd2).distinct().collect() // 4, 1, 5, 2, 3
  }
}
