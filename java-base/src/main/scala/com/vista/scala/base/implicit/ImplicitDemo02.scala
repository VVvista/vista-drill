package com.vista.scala.base.`implicit`

/**
  * @author WenTingTing by 2020/9/2
  */
object ImplicitDemo02 {
  def main(args: Array[String]): Unit = {
    implicit def addDelete(mySql: MySql): DB = {
      new DB
    }

    val mySql = new MySql
    mySql.insert
    mySql.delete // addDelete(mySql).delete
  }
}


class MySql {
  def insert: Unit = {
    println("insert")
  }
}

class DB {
  def delete: Unit = {
    println("delete")
  }
}