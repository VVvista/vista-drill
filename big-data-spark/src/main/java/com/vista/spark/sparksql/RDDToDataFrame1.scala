package com.vista.spark.sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}

/**
  * @author WenTingTing by 2020/6/5
  */
object RDDToDataFrame1 {
  // 方式2
  case class Student(id: Int, name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrameConstruct").setMaster("local")
    val sc = new SparkContext(conf)
    val textRDD = sc.textFile("D://idea//workspace//vista-drill//big-data-spark//test-file//student.txt")
    val studentRDD = textRDD.map(f => {
      val strings = f.split(" ")
      new Student(Integer.valueOf(strings(0)), strings(1), Integer.valueOf(strings(2)))
    })
    val d: RDD[Student] = studentRDD
    for (elem <- studentRDD.collect()) {
      println(elem)
    }

    val sqlContext = new SQLContext(sc)
    // 方式1
    // val studentDF = sqlContext.createDataFrame(studentRDD, classOf[Student])
    // 方式2
    import sqlContext.implicits._
    val studentDF = studentRDD.toDF()

    studentDF.show()
    studentDF.printSchema()

    val student: RDD[Row] = studentDF.rdd

/* // 方式1
   student.foreach(f => {
      println(f.get(0) + " "
        + f.getAs[Int](1) + " "
        + f.getAs[Int]("id") + " "
        + f.getString(2)
      )
    })*/

    // 方式2
    student.foreach(f => {
      println(f.get(0) + " "
        + f.getAs[String](1) + " "
        + f.getAs[String]("id") + " "
        + f.getInt(2)
      )
    })
  }
}
// 方式1
case class Student(id: Int, name: String, age: Int) extends Serializable {
  def getId = id

  def getName = name

  def getAge = age
}
