package com.vista.scala.base.iterable

import scala.collection.mutable

/**
  * @author WenTingTing by 2020/9/4
  */
object QueueDemo01 {
  def main(args: Array[String]): Unit = {
    val queue = mutable.Queue[Int]()
    queue += 9
    queue += (1, 3, 4, 3)
    queue ++= List(1, 2, 3)
    print(queue)
    queue.dequeue()
    print(queue)
    queue.enqueue(4, 5, 6)
    queue.head
    queue.last
    queue.tail
    queue.tail.tail
  }
}
