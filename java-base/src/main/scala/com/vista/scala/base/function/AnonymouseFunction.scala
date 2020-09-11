package com.vista.scala.base.function

/**
  * @author WenTingTing by 2020/9/8
  */
object AnonymouseFunction {
  def main(args: Array[String]): Unit = {
    val func = (x: Int, y: Int,z:Int) => {
      println("匿名函数被调用")
      x + y+z
    }
    println("func 类型= " + func) //func仅是输出，未执行func 类型= <function3>
    println(func(10,20,20))// 执行fanc函数
  }
}
