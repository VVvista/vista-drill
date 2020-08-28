package com.vista.spark.structuredstreaming

import org.apache.spark.sql.{Row, SparkSession}

/** dep {"id": 1, "name": "Technical Department"}
  * emp {"name": "Leo", "age": 25, "depId": 1, "gender": "male", "salary": 20000}
  *
  * @author WenTingTing by 2020/8/27
  */
object ActionOperation {

  case class Deptment(id: Long, name: String)

  case class Employee(name: String, age: Long, depId: Long, gender: String, salary: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("DepartmentAvgSalaryAndAgeStatScala")
      .getOrCreate()

    import spark.implicits._

    val deptmentDF = spark.read.json("file:///extend_data/wen/hive/department.json")
    val employeeDF = spark.read.json("file:///extend_data/wen/hive/employee.json")

    val deptmentDS = spark.read.json("file:///extend_data/wen/hive/department.json").as[Deptment]
    val employeeDS = spark.read.json("file:///extend_data/wen/hive/employee.json").as[Employee]
    //collect
    val rows: Array[Row] = employeeDF.collect()
    rows.foreach(f => println(f.getAs[String]("name")))

    val employees: Array[Employee] = employeeDS.collect()
    employees.foreach(f => println(f.age))

    // count
    employeeDF.count()
    employeeDS.count()

    //first
    val row: Row = employeeDF.first()
    val employee: Employee = employeeDS.first()

    // foreach
    employeeDF.foreach(print(_))
    employeeDS.foreach(print(_))
    // reduce
    employeeDF.map(_ => 1).reduce(_ + _)
    // show
    employeeDF.show()

    // take
    employeeDF.take(1)
  }

 }