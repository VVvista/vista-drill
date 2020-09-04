package com.vista.scala.base.`object`

/** apply
  *
  * @author WenTingTing by 2020/9/1
  */
object ApplyDemo1 {
  def main(args: Array[String]): Unit = {
    //使用 apply 方法来创建对象
    val pig = Pig("小花")  //自动 apply(pName: String)
    println(pig.name)

    val pig1 = Pig() // 自动触发 apply()
    println(pig1.name)

  }
}

object Pig{
  //编写一个 apply
  def apply(inName: String): Pig = new Pig(inName)

  def apply(): Pig = new Pig("匿名猪猪")
}

class Pig(inName: String) {
  var name: String = inName
}