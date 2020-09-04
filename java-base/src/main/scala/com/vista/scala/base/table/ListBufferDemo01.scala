package com.vista.scala.base.table

import scala.collection.mutable.ListBuffer

/**
  * @author WenTingTing by 2020/9/4
  */
object ListBufferDemo01 {
  def main(args: Array[String]): Unit = {
    val list = ListBuffer[Int]() //空的 ListBuffer
    val list1 = ListBuffer[Int](1, 2, 3)
    list.append(1, 2, 3)
    list1.append(4, 5)
    list += 4
    list += (5, 6, 7, 8)
    list ++= list1
    println(list)
    println(list1)
    val list2 = list ++ list1
    val list3 = list1 :+ 5
    val list4 = 10 +: list1
    println(list)
    println(list1)
    println(list2)
    println(list3)
    println(list4)

    list1.remove(0)
    list1 -= 2
    for (item <- list1) {
      print(s"$item ")
    }
  }
}
