package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/22
  */
object Sample {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val numberRdd = sc.parallelize(Seq(1, 2, 3, 4, 5))
    numberRdd.sample(false,0.5).collect()
    numberRdd.sample(true,1).collect()
  }
}
