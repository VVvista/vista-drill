package com.vista.scala.base.`implicit`

/**
  * @author WenTingTing by 2020/9/2
  */
object ImplicitClassDemo {
  def main(args: Array[String]): Unit = {
    implicit class DBA(val mySql: MySqlA) {
      def addSuffix(): String = s"$mySql scala"
    }

    val mySql = new MySqlA
    mySql.sayOK
    mySql.addSuffix() // 底层 DB1(mySQL).addSuffix();

  }
}

class MySqlA {
  def sayOK: Unit = println("sayOK")
}
