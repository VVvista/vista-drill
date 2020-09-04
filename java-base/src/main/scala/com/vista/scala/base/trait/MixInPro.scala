package com.vista.scala.base.`trait`

/**
  * @author WenTingTing by 2020/9/2
  */
object MixInPro {
  def main(args: Array[String]): Unit = {
    val mySQL = new MySQL6 with DB6 {
      override var sal = 10
    }
  }
}

trait DB6 {
  var sal: Int //抽象字段
  var operType: String = "insert" // 具体字段

  def insert(): Unit = {
  }
}

class MySQL6 {}