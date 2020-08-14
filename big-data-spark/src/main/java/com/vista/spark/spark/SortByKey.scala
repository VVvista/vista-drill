package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/7/27
  */
object SortByKey {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    //示例一：默认方式
    val rdd1 = sc.makeRDD(List((11,0),(10,5),(4,2),(6,1),(20,2),(8,0)))
    rdd1.sortByKey(true,1).collect()

    //示例二：自定义比较原则
    val rdd = sc.makeRDD(List((11,0),(10,5),(4,2),(6,1),(20,2),(8,0),(8,2)))
    //重写ordering
    implicit val sortIntegersByString = new Ordering[Int]{ // 创建匿名类对象Ordering，重定义key的比较原则
      override def compare(x: Int, y: Int): Int = x.toString.compareTo(y.toString)
    }
    val res =rdd.sortByKey(true,1)
    res.foreach(x => print(x + " "))
  }
}
