package com.vista.scala.base.function

/**
  * @author WenTingTing by 2020/9/8
  */
object FunParameter {
  def main(args: Array[String]): Unit = {
    def plus(x: Int) = x + 1

    val res = Array(1, 2, 3, 4).map(plus(_))
    println(res.mkString(","))
    println("puls 的函数类型 function1" + (plus _))//puls 的函数类型 function1<function1>
  }

}
