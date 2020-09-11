package com.vista.spark.sparksql.develop

import org.apache.spark.sql.catalyst.expressions._

/**
  * @author WenTingTing by 2020/9/11
  */
object SpecificInternalRowDemo {
  def main(args: Array[String]): Unit = {
    val row = new SpecificInternalRow(Array(new MutableBoolean, new MutableInt, new MutableDouble))
    val values: Array[MutableValue] = row.values
    values.foreach(f => println(f.boxed))
    values.foreach(f => println(f.getClass))
    val boolean = values(0).asInstanceOf[MutableBoolean]

    println("boolean hashcode:" + boolean.hashCode())
    println("values(0) hashcode:" + values(0).hashCode())
    println("boolean boxed:" + boolean.boxed)
    println("values(0) boxed:" + values(0).boxed)

    row.setBoolean(0, true)
    row.setInt(1, 1)
  //  row.setDouble(2, 1.2d)

    val value: Array[MutableValue] = row.values
    value.foreach(f => println(f.boxed))
    println("boolean boxed:" + boolean.boxed)
    println("values(0) boxed:" + values(0).boxed)
    println("boolean boxed:" + boolean.getClass)
    println("values(2) boxed:" + values(2).getClass)

  }

}
