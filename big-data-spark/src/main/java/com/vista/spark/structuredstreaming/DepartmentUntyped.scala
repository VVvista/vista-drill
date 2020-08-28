package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession

/** 计算部门平均年龄与薪资
  * 计算部门性别平均年龄与薪资
  * dep {"id": 1, "name": "Technical Department"}
  * emp {"name": "Leo", "age": 25, "depId": 1, "gender": "male", "salary": 20000}
  *
  * @author WenTingTing by 2020/8/26
  */
object DepartmentUntyped {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("DepartmentAvgSalaryAndAgeStatScala")
      .getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val deptmentDF = spark.read.json("file:///extend_data/wen/hive/department.json")
    val employeeDF = spark.read.json("file:///extend_data/wen/hive/employee.json")
    deptmentDF.show()
    deptmentDF.printSchema()

    deptmentDF.join(employeeDF, $"id" === $"depId").
      groupBy(deptmentDF("id"))
      .agg(avg(employeeDF("age")), avg(employeeDF("salary")))
      .show()

deptmentDF.select()
    employeeDF.filter("age>20")
      .join(deptmentDF, $"depId" === $"id")
      .groupBy(deptmentDF("name"), employeeDF("gender"))
      .agg(avg(employeeDF("salary")), avg(employeeDF("age")))
      .show()

  }

}
