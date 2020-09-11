package com.vista.scala.base.generic

/**
  * @author WenTingTing by 2020/9/8
  */
object GenericDemo01 {
  def main(args: Array[String]): Unit = {
    val iMessage = new IntMessage(10)
    println(iMessage.get)
    val sMessage = new StringMessage("hello")
    println(sMessage.get)
    val dog = new Dog
    dog.dog("小狗")
  }
}

abstract class Message[T](s: T) {
  def get = s
}

class IntMessage[Int](x: Int) extends Message[Int](x)

class StringMessage[String](s: String) extends Message(s)


class Dog{
  def dog[T](t:T): Unit = println(s"$t")
}