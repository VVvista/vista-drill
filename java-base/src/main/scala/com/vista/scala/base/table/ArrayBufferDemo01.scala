package com.vista.scala.base.table

import scala.collection.mutable.ArrayBuffer

/**
  * @author WenTingTing by 2020/9/3
  */
object ArrayBufferDemo01 {
  def main(args: Array[String]): Unit = {
    val buffer = ArrayBuffer[Int]()
    val arr = ArrayBuffer[Any](3, 2, 5)
    arr.append(5, 6, 7)
    arr(0) = 7
    arr.remove(0)
    for (item <- arr) {
      println(item)
    }
  }
}
