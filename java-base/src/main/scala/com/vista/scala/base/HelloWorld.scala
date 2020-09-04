package com.vista.scala.base

/**
  * @author WenTingTing by 2020/8/25
  */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    val helloWorld = new HelloWorld
    println(helloWorld.getName)
    println(helloWorld.sayHello)
    println(helloWorld.sayHello)
    val array = Array(1, 2, 3, 4, 5)

  }
}

class HelloWorld {
  private[this] var name: String = "zhao"

  def sayHello = {
    name = "wen"
    "hello" + name
  }

  def getName = name
}
