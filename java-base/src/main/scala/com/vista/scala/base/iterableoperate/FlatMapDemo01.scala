package com.vista.scala.base.iterableoperate

/**
  * @author WenTingTing by 2020/9/7
  */
object FlatMapDemo01 {
  def main(args: Array[String]): Unit = {
    (1 to 5).foreach(print(_)) //12345
    println()
    (1 to 5).par.foreach(print(_))//并行计算，结果不一定顺序输出 ：41532
  }

}
