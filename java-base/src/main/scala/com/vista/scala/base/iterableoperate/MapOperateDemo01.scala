package com.vista.scala.base.iterableoperate

import scala.collection.mutable._

/**
  * @author WenTingTing by 2020/9/7
  */
object MapOperateDemo01 {
  def main(args: Array[String]): Unit = {
    val list = List(2, 3, 4, 5, 6, 7, 8)
    val buffer = ListBuffer[Int]()
    for (item <- list) {
      buffer += item
    }
    println(buffer.toArray)


    // 高阶函数
    val s = test(sum, 2.0)
    println(s)
    val f = sum _
    println(f(1.2))

    // map操作
    val ints: List[Int] = list.map(multiple)

    val names = List("Alice", "Bob", "Nick")

    names.map(upper)
    val list1 = names.map(_.toUpperCase)

    // flatMap
    val flat = List("Alice Bob Nick", "leo tom ")
    flat.flatMap(_.split(" "))

    // filter
    names.filter(_.startsWith("A"))

  }

  def upper(s: String) = {
    s.toUpperCase
  }

  def multiple(i: Int): Int = {
    i * 2
  }

  def test(f: Double => Double, n: Double) = {
    f(n)
  }


  def sum(d: Double): Double = {
    d * 2
  }


}


object MapOperateDemo02 {
  def main(args: Array[String]): Unit = {
    /*
    /*
    请将 List(3,5,7) 中的所有元素都 * 2 ，
    将其结果放到一个新的集合中返回，即返回一个新的 List(6,10,14), 请编写程序实现.
    */
    */
    val list = List(3, 5, 7, 9)
    //说明 list.map(multiple) 做了什么
    //1. 将 list 这个集合的元素 依次遍历
    //2. 将各个元素传递给 multiple 函数 => 新 Int
    //3. 将得到新 Int ,放入到一个新的集合并返回
    //4. 因此 multiple 函数调用 3
    val list2 = list.map(multiple)
    println("list2=" + list2) //List(6,10,14)
  }

  def multiple(n: Int): Int = {
    println("multiple 被调用~~")
    2 * n
  }
}


object ReduceExercise01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)

    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }
    // (((1-2) - 3) - 4) - 5 = -13
    println(list.reduceLeft(minus)) // 输出? -13
    // 1 - (2 - (3 -(4 - 5))) = 3
    println(list.reduceRight(minus)) //输出? 3
    // reduce 等价于 reduceLeft
    println(list.reduce(minus))
    println("minval=" + list.reduceLeft(min)) // 1
  }

  //求出最小值
  def min(n1: Int, n2: Int): Int = {
    if (n1 > n2) n2 else n1
  }
}