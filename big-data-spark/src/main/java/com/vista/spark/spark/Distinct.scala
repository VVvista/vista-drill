package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/22
  */
object Distinct {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val rdd1 = sc.parallelize(Seq(1, 2, 3, 3))
    rdd1.distinct().collect()
  }
}