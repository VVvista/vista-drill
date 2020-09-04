package com.vista.scala.base.table

/**
  * @author WenTingTing by 2020/9/3
  */
object ArrayDemo01 {
  def main(args: Array[String]): Unit = {
    val arr = new Array[Int](3) // 底层Int[] arr=new Int[3]
    val array = Array(1, 2, 3, 4, 5)
    arr(0) = 1
    arr(1) = 2
    arr(2) = 3
    println(arr.length)
    for (item <- arr) {
      println(item)
    }

    for (index <- arr.indices) { //def indices: Range = 0 until length
      println(arr(index))
    }
  }
}
