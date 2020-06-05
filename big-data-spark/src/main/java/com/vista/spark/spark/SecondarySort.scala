package com.vista.spark.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

/** 二次排序
  *
  * 排序字段必须具有可比较性：
  * a.自定义排序字段： 方式1.封装成对象，对象实现可比较接口  方式2. 使用比较器，自定义比较规则：new Ordering[T]
  * b.调用sortByKey()或sortBy(xx...) ( sortBy(xx...)底层调用sortByKey() )
  *
  * 注意：scala中用于排序：Ordered和Ordering
  * Ordered继承了java中的Comparable接口
  * Ordering继承了java的Comparator接口
  *
  * 实现方式：
  *
  * 一.排序字段封装成对象：secondarySort
  * SecondarySort类实现Ordered、Serializable接口，重写compare()方法：使类具有可比较性
  *
  * 1.rdd.map(f=>(secondarySort,f)).sortByKey():将secondarySort对象转化成rdd的key，调用sortByKey()算子
  * 2.rdd.sortBy(f=>new SecondarySort(xx,xx))：调用sortBy()算子时将比较字段设置成secondarySort对象
  *
  * 此两种方式在比较时，底层调用secondarySort.compare()方法
  *
  * 二.排序字段自定义 比较器
  * 在driver程序中，创建 new Ordering[Int] 匿名内部类，重写compare() 方法
  *
  * 1.implicit val sortIntegersByString = new Ordering[Int] {
  * override def compare(x: Int, y: Int): Int = x.toString.compareTo(y.toString)
  * }
  * rdd.sortByKey()
  *
  * 调用sortByKey()之前，创建new Ordering[Int] 对象，设置排序规则。
  * (底层sortIntegersByString对象是改写了sortByKey()底层的Ordering对象，所以自定义规则才生效)
  *
  * 2.rdd.sortBy(x => (x._1, x._2), true, 1)(new Ordering[(Int, Int)] {
  * override def compare(x: (Int, Int), y: (Int, Int)): Int = {
  * var res = x._1 - y._1
  * if (res == 0) {
  * res = x._2 - y._2
  * }
  * res
  * }
  * }, ClassTag.Object.asInstanceOf[ClassTag[(Int, Int)]])
  *
  * sortBy()中传入隐式比较参数。
  *
  * 总结：
  * 此两种方式仅是使用比较接口和比较器的区别
  * new Ordering[T]中的比较类型与key[T]类型必须保持一致
  *
  * @author WenTingTing by 2020/5/27
  */
object SecondarySort {
  val conf = new SparkConf().setAppName("SecondarySort").setMaster("local")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    //secondarySort1
    //    secondarySort2
    //   secondarySort3
     secondarySort4
  }

  /**
    * 对象+sortByKey()
    */
  def secondarySort1(): Unit = {
    val file = sc.textFile("D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\secondSort.txt")
    val result = file.map(f => {
      val strings = f.split(" ")
      val key = new SecondarySort(strings(0).toInt, strings(1).toInt)
      (key, f)
    })
      .sortByKey()
      .collect()
    result.foreach(f => println(f))
  }

  /**
    * 对象+sortBy()
    */
  def secondarySort2(): Unit = {
    val file = sc.textFile("D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\secondSort.txt")
    val result = file.map(f => {
      val strings = f.split(" ")
      (strings(0).toInt, strings(1).toInt)
    })
      .sortBy(f => new SecondarySort(f._1, f._2))
      .collect()
    result.foreach(f => println(f))
  }

  /**
    * 比较器+sortByKey()
    */
  def secondarySort3(): Unit = {
    val file = sc.textFile("D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\secondSort.txt")
    val map = file.map(f => {
      val strings = f.split(" ")
      (strings(0).toInt, strings(1).toInt)
    })

    // 自定义比较器
    new Ordering[Int] {
      override def compare(x: Int, y: Int): Int = {
        x.toString.compareTo(y.toString)
      }
    }

    val result = map
      .sortByKey()
      .collect()
    result.foreach(f => println(f))
  }

  /**
    * 比较器+sortBy()
    */
  def secondarySort4(): Unit = {
    val file = sc.textFile("D:\\idea\\workspace\\vista-drill\\big-data-saprk\\test-file\\secondSort.txt")
    val map = file.map(f => {
      val strings = f.split(" ")
      (strings(0).toInt, strings(1).toInt)
    })

    val result = map.sortBy(f => f._1)(new Ordering[Int] { // 自定义比较器
      override def compare(x: Int, y: Int): Int = {
        x.toString.compareTo(y.toString)
      }
    }, ClassTag.Object.asInstanceOf[ClassTag[Int]])
      .collect()
    result.foreach(f => println(f))
  }

}


class SecondarySort(val first: Int, val second: Int) extends Ordered[SecondarySort] with Serializable {

  override def compare(o: SecondarySort): Int = {
    var result = this.first - o.first
    if (result == 0) result = this.second - o.second
    result
  }

  override def toString: String = first + ":" + second
}