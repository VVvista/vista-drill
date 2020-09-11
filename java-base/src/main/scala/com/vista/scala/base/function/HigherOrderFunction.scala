package com.vista.scala.base.function

/**
  * @author WenTingTing by 2020/9/8
  */
object HigherOrderFunction {
  def main(args: Array[String]): Unit = {
    test1
  }
  def test2: Unit ={
    def minusxy(x: Int): Int => Int = {
      (y: Int) => x - y // 匿名函数
    }
    val f1 = minusxy(3) // f1即为一个闭包
    println("f1 的类型=" + f1) //f1 的类型=<function1>
    println(f1(1)) // 2
    println(f1(9)) // -6
  }

  def test1: Unit = {
    def minusxy(x: Int): Int => Int = {
      (y: Int) => x - y // 匿名函数
    }

    val f1 = minusxy(3)
    println("f1 的类型=" + f1) //f1 的类型=<function1>
    println(f1(1)) // 2
    println(f1(9)) // -6
  }

  def test: Unit = {
    def test(f: Double => Double, f2: Double => Int, n1: Double) = {
      f(f2(n1))
    }

    //sum 是接收一个 Double,返回一个 Double
    def sum(d: Double): Double = d * 2

    def mod(d: Double): Int = d.toInt % 2

    val res = test(sum, mod, 5.0)
    println(res) // 2.0
  }
}
