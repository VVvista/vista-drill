package com.vista.scala.base.generic

/**
  * @author WenTingTing by 2020/9/8
  */
object LowerBoundsDemo01 {
  def main(args: Array[String]): Unit = {
    biophony(Seq(new Earth, new Earth)).map(_.eat())// map只能映射到Earth中方法
    biophony(Seq(new Animal, new Animal)).map(_.sound())
    biophony(Seq(new Bird, new Bird)).map(_.sound()) //使用多态，将bird映射为Animal类型，但sound方法认识执行bird对象： bird soundsbird sounds
    biophony(Seq(new Moon, new Moon)) // 使用多态，将Moon映射为Object类型,但Object无法映射到
   //  biophony(Seq(new Moon, new Moon)).map(_.sound()) // 报错：使用多态，将Moon映射为Object类型,但Object无sound方法，所以报错
  }

  //下界
  def biophony[T >: Animal](things: Seq[T]) = things
}

class Earth { //Earth 类
  def sound() { //方法
    println("hello !")
  }
  def eat() { //方法
    println("hello eat!")
  }
}

class Animal extends Earth {
  override def sound() = { //重写了 Earth 的方法 sound()
    println("animal sound")
  }
}

class Bird extends Animal {
  override def sound() = { //将 Animal 的方法重写
    print("bird sounds")
  }
}

class Moon {
  def sound() = {
    print("moon sounds")
  }
}
