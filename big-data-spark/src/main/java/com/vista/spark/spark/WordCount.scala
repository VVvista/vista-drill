package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/5/22
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordCount").setMaster("local")
    val sc = new SparkContext(conf)

    val list = Array(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6)
    val listRDD = sc.parallelize(list).cache()
    println(listRDD.collect())
    println(listRDD.count())

    /**
      * wordCount程序
      */
        val lines = sc.textFile("file:\\D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\hello.txt")
        val words = lines.flatMap(line => line.split(" "));
        val pairs = words.map(word => (word, 1))
        val reduce = pairs.reduceByKey(_ + _);
        println( reduce.count())

  }

}
