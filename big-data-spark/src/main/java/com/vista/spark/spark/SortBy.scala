package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

/** sortBy()算子
  *
  * @author WenTingTing by 2020/5/27
  */
object SortBy {
  val conf = new SparkConf().setAppName("wordCount").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    sortFileFields()

  }

  /**
    * 按照单词个数排序
    * 使用sortBy()算子自定义排序字段和升降
    */
  def sortFileFields(): Unit = {
    val file = sc.textFile("D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\hello.txt")
    val result = file.flatMap(f => f.split(" "))
      .map(f => (f, 1))
      .reduceByKey(_ + _)
      .sortBy(f => (f._2, f._1), false)
      .collect()
    result.foreach(f => println(f))

  }

  /**
    * 自定义排序规则
    */
  def sortBy(): Unit = {
    val rdd = sc.makeRDD(List((11, 0), (10, 5), (4, 2), (6, 1), (20, 2), (8, 0)))
    //重写ordering
    /*
        implicit val sortIntegersByString = new Ordering[Int] {
          override def compare(x: Int, y: Int): Int = x.toString.compareTo(y.toString)
        }
    */
    val res = rdd.sortBy(x => x, true, 1)(new Ordering[(Int, Int)] {
      override def compare(x: (Int, Int), y: (Int, Int)): Int = {
        var res = x._1 - y._1
        if (res == 0) {
          res = x._2 - y._2
        }
        res
      }
    }, ClassTag.Object.asInstanceOf[ClassTag[(Int, Int)]])


  }
}
