package com.vista.scala.base.iterableoperate

/**
  * @author WenTingTing by 2020/9/7
  */
object ReduceDemo01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 20, 30, 4, 5)

    //reduce
    println(list.reduceLeft(_ - _))
    println(list.reduceLeft(Math.min(_, _)))
    println(list.reduceRight(_ - _))

    //fold
    println(list.foldLeft(1)(_ - _))
    println(list.foldRight(0)(_ - _))

    //scan
    println(list.scanLeft(1)(_ - _))
    println(list.scanRight(0)(_ - _))

    // zip
    val list1 = List(1, 2, 3)
    val list2 = List(4, 5, 6, 7)
    val tuples: List[(Int, Int)] = list1.zip(list2)
    println(tuples)


    // iterable
    val iter = list1.iterator
    iter.foreach(f => {
      f
    })
    for (item <- iter) {
      println(item)
    }
  }

}

object FoldDemo01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)

    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }
    //说明
    //1. 折叠的理解和化简的运行机制几乎一样.
    //理解 list.foldLeft(5)(minus) 理解成 list(5,1, 2, 3, 4) list.reduceLeft(minus)
    //步骤 (5-1)
    //步骤 ((5-1) - 2)
    //步骤 (((5-1) - 2) - 3)
    //步骤 ((((5-1) - 2) - 3)) - 4 = - 5
    println(list.foldLeft(5)(minus)) // 函数的柯里化
    ////理解 list.foldRight(5)(minus) 理解成 list(1, 2, 3, 4, 5) list.reduceRight(minus)
    // 步骤 (4 - 5)
    // 步骤 (3- (4 - 5))
    // 步骤 (2 -(3- (4 - 5)))
    // 步骤 1- (2 -(3- (4 - 5))) = 3
    println(list.foldRight(5)(minus)) //
  }
}

object ScanDemo01 {
  def main(args: Array[String]): Unit = {
    //普通函数
    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    //5 (1,2,3,4,5) =>(5, 4, 2, -1, -5, -10) //Vector(5, 4, 2, -1, -5, -10)
    val i8 = (1 to 5).scanLeft(5)(minus) //IndexedSeq[Int]
    println("i8=" + i8)

    //普通函数
    def add(num1: Int, num2: Int): Int = {
      num1 + num2
    }

    //(1,2,3,4,5) 5 => (20,19,17,14, 10,5)
    val i9 = (1 to 5).scanRight(5)(add) //IndexedSeq[Int]
    println("i9=" + i9)
  }
}


object IteratorDemo01 {
  def main(args: Array[String]): Unit = {
    val iterator = List(1, 2, 3, 4, 5).iterator // 得到迭代器
    /*
    这里我们看看 iterator 的继承关系
    def iterator: Iterator[A] = new AbstractIterator[A] {
    var these = self
    def hasNext: Boolean = !these.isEmpty
    def next(): A =
    if (hasNext) {
    val result = these.head; these = these.tail; result
    } else Iterator.empty.next()
    */
    println("--------遍历方式 1 while -----------------")
    while (iterator.hasNext) {
      println(iterator.next())
    }
    println("--------遍历方式 2 for -----------------")
    for (enum <- iterator) {
      println(enum) //
    }
  }
}


object ViewDemo01 {
  def main(args: Array[String]): Unit = {
    def multiple(num: Int): Int = num

    //如果这个数，逆序后和原来数相等，就返回 true,否则返回 false
    def eq(i: Int): Boolean = {
      println("eq 被调用..")
      i.toString.equals(i.toString.reverse)
    }

    //说明: 没有使用 view,常规方式
    val viewSquares1 = (1 to 100).filter(eq)
    println(viewSquares1)
    //使用 view，来完成这个问题,程序中，对集合进行 map,filter,reduce,fold...
    //你并不希望立即执行，而是在使用到结果才执行，则可以使用 view 来进行优化.
    val viewSquares2 = (1 to 100).view.filter(eq)
    println(viewSquares2)
    //遍历
    for (item <- viewSquares2) {
      println("item=" + item)
    }

    (1 to 5).foreach(println(_))
    println()
    (1 to 5).par.foreach(println(_))
  }
}