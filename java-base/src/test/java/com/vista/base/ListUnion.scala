package com.vista.base

/**
  * @author WenTingTing by 2020/7/9
  */
object ListUnion {
  def main(args: Array[String]): Unit = {
    val list1 = Seq[Int](1, 2, 3, 4)
    val list2 = Seq[Int](3, 4, 5, 6, 7)
    println((list1.diff(list2)).union(list2.diff(list1)))


  }

}
