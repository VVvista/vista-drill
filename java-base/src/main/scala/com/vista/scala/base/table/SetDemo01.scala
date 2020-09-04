package com.vista.scala.base.table

import scala.collection.mutable

/**
  * @author WenTingTing by 2020/9/4
  */
object SetDemo01 {
  def main(args: Array[String]): Unit = {
    val set = Set(1, 2, 3, 3)
    println(set)
    val set1 = mutable.Set(1, 2, 3, 3)
    val set2 = mutable.Set[String]()
    set2.add("d")
    // 添加
    set1.add(4)
    set1 += 5
    set1 += (5, 6, 7)
    println(set1)
    // 删除
    set1.remove(2)
    set1-=(1,2)
    println(set1)
    // 删除
    for (item<-set1){
      println(item)
    }
  }
}
