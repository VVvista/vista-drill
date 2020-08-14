package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
  * @author WenTingTing by 2020/7/22
  */
object MapPartitionsWithIndex {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    val numberRdd = sc.parallelize(Seq(1, 2, 3, 4, 5),3)

    val result = numberRdd.mapPartitionsWithIndex((index, f) => {
      val list = ArrayBuffer[String]()
      while (f.hasNext) {
        list.append(s"${index}_${f.next()}")
      }
      list.iterator
    })

    result.collect().foreach(println(_))
  }

}
