package com.vista.scala.base.generic

/** 编写一个通用的类，可以进行 Int 之间、Float 之间、等实现了 Comparable 接口的值直接的比较.
  * java.lang.Integer
  *
  * @author WenTingTing by 2020/9/8
  */
object UpperBoundsDemo01 {
  def main(args: Array[String]): Unit = {
    // 此处Integer：java.lang.Integer
    val com = new CommonCompare(Integer.valueOf(10), Integer.valueOf(20))
    println(com.greater)
    // 此处使用：java.lang.Float,创建Float对象
    val com1 = new CommonCompare(java.lang.Float.valueOf(10.2f), java.lang.Float.valueOf(20.1f))
    println(com1.greater)
    // 等价于，此处使用隐式转换，将scala float 11.2f 转换为java.lang.Float 11.2f
    //implicit def float2Float(x: Float): java.lang.Float = x.asInstanceOf[java.lang.Float]
    val com2 = new CommonCompare[java.lang.Float](11.2f ,23.3f)
    println(com2.greater)
  }
}

class CommonCompare[T <: Comparable[T]](n1: T, n2: T) {
  def greater = if (n1.compareTo(n2) > 0) n1 else n2
}
