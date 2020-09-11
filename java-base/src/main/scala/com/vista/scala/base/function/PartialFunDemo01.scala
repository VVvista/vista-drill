package com.vista.scala.base.function

/**
  * @author WenTingTing by 2020/9/8
  */
object PartialFunDemo01 {
  def main(args: Array[String]): Unit = {

    test3

  }

  def test3: Unit = {
    // 偏函数简写2
    val list = Array(1, 2, 3, 1.9f, "hello")
    list.collect {
      case i: Int => i + 1
      case j: Float => (j * 2).toInt
    } foreach (println(_))

  }

  def test2: Unit = {
    // 偏函数简写1
    def partialFun2: PartialFunction[Any, Int] = {
      case i: Int => i + 1
      case j: Float => (j * 2).toInt
    }

    val list = Array(1, 2, 3, 1.9f, "hello")
    list.collect(partialFun2).foreach(println(_))

  }

  def test: Unit = {

    // 创建偏函数
    val func = new PartialFunction[Any, Int] {
      override def isDefinedAt(x: Any): Boolean = {
        x.isInstanceOf[Int]
      }

      override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] + 1
      }
    }

    // 使用偏函数
    val list = Array(1, 2, 3, "hello")
    list.collect(func).foreach(println(_))
  }
}
