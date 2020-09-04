package com.vista.scala.base

/**
  * @author WenTingTing by 2020/8/28
  */
object Cat {
  def main(args: Array[String]): Unit = {
    val cat = new Cat()
    cat.name="tom"
   // cat.name_("leo")
    cat.age = 10
    cat.color = "red"
    print(cat.toString)
  }
}

class Cat {
  var name: String = _
  var age: Int = _
  var color: String = _

  def name_(newName:String) = {
    name = newName + "_"
  }

  override def toString: String = s"name:$name,age:$age,color:$color"
}
