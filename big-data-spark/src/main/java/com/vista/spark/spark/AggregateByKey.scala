package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/23
  */
object AggregateByKey {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val rdd1 = sc.parallelize(Seq(1, 2, 3, 3, 2, 3))
    rdd1.map((_, 1)).aggregateByKey(0)(_ + _, _ + _).collect()
  }
}
