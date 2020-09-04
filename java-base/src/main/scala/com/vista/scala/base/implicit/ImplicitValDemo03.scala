package com.vista.scala.base.`implicit`


/**
  * @author WenTingTing by 2020/9/2
  */
object ImplicitValDemo03 {
  implicit val name: String = "Jack"

  def main(args: Array[String]): Unit = {
    implicit val name: String = "leo"

    def hello(implicit name: String = "Tom"): Unit = {
      println(s"name:$name")
    }

    hello // 底层：hello("leo")
    hello("rose")
  }
}
