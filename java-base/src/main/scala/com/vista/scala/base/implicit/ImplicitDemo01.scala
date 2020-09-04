package com.vista.scala.base.`implicit`


/**
  * @author WenTingTing by 2020/9/2
  */
object ImplicitDemo01 {
  def main(args: Array[String]): Unit = {
    implicit def f1(d: Double): Int = {
      d.toInt
    }
    val i: Int = 7.1
    println(i)
  }
}
