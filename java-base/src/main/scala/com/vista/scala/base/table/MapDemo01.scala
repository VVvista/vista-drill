package com.vista.scala.base.table

import scala.collection.immutable
import scala.collection.mutable

/**
  * @author WenTingTing by 2020/9/4
  */
object MapDemo01 {
  def main(args: Array[String]): Unit = {
    val map = immutable.Map("leo" -> 12, "jack" -> 23, "rose" -> 34)
    print(map)

    val map1 = mutable.Map("leo" -> 12, "jack" -> 23, "rose" -> 34)
    val map2 = mutable.Map[String, Int]() // 空map
    val map3 = mutable.Map(("leo", 20), ("jack", 23))
    map1("leo") = 20
    map2("leo") = 20
    println(map1)
    println(map2)
    println(map3)

    // 取值
    map1("rose")
    map1.contains("jack")
    map1.get("jack").get
    map1.getOrElse("jack", "TT")

    // 删除
    map1.remove("web")
    map1 -= "rose"
    map1 -= ("rose", "mark") //key不存在不报错
    println(map1)
    map1 -= "rose"

    // 添加
    map2 += ("tom" -> 11, "jerry" -> 32)
    map2.put("ting",4)

    // 遍历
    for (keys <- map1.keys) {}
    for (values <- map1.values) {}
    for ((key, value) <- map1) {}
    for (item <- map1) {
      print(item._1, item._2)
    }
  }

}
