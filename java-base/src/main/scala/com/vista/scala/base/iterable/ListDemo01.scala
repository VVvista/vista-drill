package com.vista.scala.base.iterable

/**
  * @author WenTingTing by 2020/9/3
  */
object ListDemo01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3) //创建时，直接分配元素
    val list0 = Nil
    list.head
    list.last
    list(2)

    // 添加，列表中添加新元素，返回新列表，列表无变化
    val list1 = List("a", "b", "c")
    val list2 = list :+ 4 // list 无变化
    println(list2)// List(1, 2, 3, 4)
    val list3 = 4 +: list
    println(list3)// List(4, 1, 2, 3)
    val list4 = 4 :: 5 :: 6 :: list //集合对象一定要放置在最右边
    val list41 = 4 :: 5 :: 6 :: list :: Nil//集合对象一定要放置在最右边
    println(list)// List(1, 2, 3)
    println(list4)// List(4, 5, 6, 1, 2, 3)
    println(list41)// List(4, 5, 6, List(1, 2, 3))
    val list5 = 4 :: 5 :: 6 :: list ::: Nil
    println(list)// List(1, 2, 3)
    println(list5)// List(4, 5, 6, 1, 2, 3)
  }

}
