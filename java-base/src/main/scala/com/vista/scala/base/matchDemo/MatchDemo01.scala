package com.vista.scala.base.matchDemo

/**
  * @author WenTingTing by 2020/9/7
  */
object MatchDemo01 {


  def main(args: Array[String]): Unit = {
    test7
  }

  def test: Unit = {

    val oper = ' '
    val n1 = 20
    val n2 = 10
    var res = 0
    oper match {
      case '+' => res = n1 + n2
      case '-' => res = n1 - n2
      case '*' => res = n1 * n2
      case '/' => res = n1 / n2
      case _ => println("未匹配到")
    }
    println(res)
  }

  def test2: Unit = {
    for (ch <- "+-3!") { //是对"+-3!" 遍历
      var sign = 0
      var digit = 0
      ch match {
        case '+' => sign = 1
        case '-' => sign = -1
        case _ if ch.toString.equals("3") => digit = 3
        case _ if ch > 10000 => digit = 4
        case _ => sign = 2
      }
      println(ch + " " + sign + " " + digit)
    }
    /*    + 1 0
        - -1 0
        3 0 3
        ! 2 0*/
  }

  def test3: Unit = {
    val ch = 'U'
    ch match {
      case '+' => println("ok~")
      // 下面 case mychar 含义是 mychar = ch
      case mychar => println(mychar)
      case _ => println("_")
    }

    val ch1 = '+'
    val res = ch1 match {
      case '+' => ch1 + " hello "
      case _ => println("ok~~")
    }
    println("res=" + res)
  }

  def test4: Unit = {
    val a = 8
    //说明 obj 实例的类型 根据 a 的值来返回
    val obj = if (a == 1) 1
    else if (a == 2) "2"
    else if (a == 3) BigInt(3)
    else if (a == 4) Map("aa" -> 1)
    else if (a == 5) Map(1 -> "aa")
    else if (a == 6) Array(1, 2, 3)
    else if (a == 7) Array("aa", 1)
    else if (a == 8) Array("aa")


    val result = obj match {
      case a: Int => a
      case _: Map[String, Int] => "对象是一个字符串-数字的 Map 集合"
      case _: Map[Int, String] => "对象是一个数字-字符串的 Map 集合"
      case _: Array[String] => "对象是一个字符串数组"
      case _: Array[Int] => "对象是一个数字数组"
      case _: BigInt => Int.MaxValue
      case _ => "no match "
    }
    println(result)
  }

  def test5: Unit = {
    val arrs = Array(Array(0), Array(1, 0), Array(0, 1, 0), Array(1, 1, 0), Array(1, 1, 0, 1))

    for (arr <- arrs) {
      val result = arr match {
        case Array(0) => "0"
        case Array(x, y) => x + "=" + y
        case Array(0, _*) => "以 0 开头和数组"
        case _ => "什么集合都不是"
      }
      /* result = 0
         result = 1 = 0
         result = 以 0 开头和数组
         result = 什么集合都不是
        result = 什么集合都不是*/
      println("result = " + result)
    }
  }

  def test6: Unit = {
    for (list <- Array(List(0), List(1, 0), List(88), List(0, 0, 0), List(1, 0, 0))) {
      val result = list match {
        case 0 :: Nil => "0"
        case x :: y :: Nil => x + " " + y
        case 0 :: tail => "0 ..."
        case x :: Nil => x
        case _ => "something else"
      }

      /* 0
         1 0
         88
         0 ...
       */
      println(result)
    }

  }

  def test7: Unit = {
    for (pair <- Array((0, 1), (1, 0), (10, 30), (1, 1), (1, 0, 2))) {
      val result = pair match {
        case (0, _) => "0 ..."
        case (y, 0) => y
        case (x, y) => (y, x) //"匹配到(x,y)" + x + " " + y
        case _ => "other"
      }
/*    0 ...
      1
      (30,10)
      (1,1)
      other*/
      println(result)
    }

  }
}
