package com.vista.scala.base.iterable

/**
  * @author WenTingTing by 2020/9/3
  */
object TupleOperate {
  def main(args: Array[String]): Unit = {
    val tuple = (1, 2, "hello", 7.1)
    println(tuple)
    println(tuple._3)

    /**
      * override def productElement(n: Int) = n match {
      * case 0 => _1
      * case 1 => _2
      * case 2 => _3
      * case 3 => _4
      * case _ => throw new IndexOutOfBoundsException(n.toString())
      * }
      */
    println(tuple.productElement(0))

   for (item<- tuple.productIterator){
     println(item)
   }
  }
}
