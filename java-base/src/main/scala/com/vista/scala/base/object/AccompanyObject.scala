package com.vista.scala.base.`object`

/**
  * @author WenTingTing by 2020/8/31
  */
object AccompanyObject {
  def main(args: Array[String]): Unit = {
    println(ScalaPerson.sex)
    ScalaPerson.sex = false
    ScalaPerson.sayHi
  }
}

object ScalaPerson {
  var sex: Boolean = true

  def sayHi {

    println("object scalaPerson sayHi~~")
  }
}

class ScalaPerson {
  ScalaPerson.sex
  var name: String = _

}
