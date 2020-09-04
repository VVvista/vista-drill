package com.vista.scala.base.table

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

/**
  * @author WenTingTing by 2020/9/3
  */
object ArrayBuffer2JavaList {
  def main(args: Array[String]): Unit = {
    val arr = ArrayBuffer[String]("1", "2", "3")

    /**
      * implicit def bufferAsJavaList[A](b: mutable.Buffer[A]): ju.List[A] = b match {
      * case JListWrapper(wrapped) => wrapped
      * case _ => new MutableBufferWrapper(b)
      * }
      */
    import scala.collection.JavaConversions.bufferAsJavaList
    val javaArr = new ProcessBuilder(arr)
    val list = javaArr.command()
    println(list.getClass) // [1, 2, 3]

    import scala.collection.JavaConversions.asScalaBuffer
    val scalaArr: mutable.Buffer[String] = list


  }
}
