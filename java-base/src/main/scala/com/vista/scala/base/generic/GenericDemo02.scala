package com.vista.scala.base.generic

import com.vista.scala.base.generic.SeasonEnum.SeasonEnum

/**
  * @author WenTingTing by 2020/9/8
  */
object GenericDemo02 {
  def main(args: Array[String]): Unit = {
    val class1 = new EnglishClass[SeasonEnum, String, String](SeasonEnum.spring, "01班", "高级班")
    val class2 =  new EnglishClass[SeasonEnum, String, Int](SeasonEnum.spring, "01班", 1)
  }
}

class EnglishClass[A, B, C](val season: A, val name: B, val classType: C)

object SeasonEnum extends Enumeration {
  type SeasonEnum = Value
  val spring, autumn, summer, winter = Value
}