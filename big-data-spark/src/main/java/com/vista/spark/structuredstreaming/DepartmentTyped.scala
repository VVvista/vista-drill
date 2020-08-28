package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession

/** 计算部门平均年龄与薪资
  * 计算部门性别平均年龄与薪资
  * dep {"id": 1, "name": "Technical Department"}
  * emp {"name": "Leo", "age": 25, "depId": 1, "gender": "male", "salary": 20000}
  *
  * @author WenTingTing by 2020/8/27
  */
object DepartmentTyped {

  case class Deptment(id: Long, name: String)

  case class Employee(name: String, age: Long, depId: Long, gender: String, salary: Long)


  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("DepartmentAvgSalaryAndAgeStatScala")
      .getOrCreate()

    import spark.implicits._

    val deptmentDS = spark.read.json("file:///extend_data/wen/hive/department.json").as[Deptment]
    val employeeDS = spark.read.json("file:///extend_data/wen/hive/employee.json").as[Employee]
    deptmentDS.show()
    employeeDS.printSchema()
    val frame = employeeDS.groupBy($"depId").avg("age")


  }
}
