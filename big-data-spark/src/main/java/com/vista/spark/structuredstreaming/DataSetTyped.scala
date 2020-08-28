package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession

/**
  * @author WenTingTing by 2020/8/27
  */
object DataSetTyped {

  case class Employee(name: String, age: Long, depId: Long, gender: String, salary: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("DataSetTyped")
      .getOrCreate()

    import spark.implicits._

    val employeeDS = spark.read.json("file:///extend_data/wen/hive/employee.json").as[Employee]
    val employeeDS2 = spark.read.json("file:///extend_data/wen/hive/employee.json").as[Employee]

    // coalesce、repartition
    /*  employeeDS.coalesce(1)
     */ employeeDS.repartition(1)

    println(employeeDS.rdd.partitions.length)

    // distinct和dropDuplicates
    employeeDS.distinct()
    employeeDS.dropDuplicates("depId")

    // except、filter、intersect
    employeeDS.except(employeeDS2)
    employeeDS.intersect(employeeDS2)
    employeeDS.filter("age< 20")
    employeeDS.filter(employee => employee.age < 20)

    //map、flatMap、mapPartitions
    employeeDS.map(employee => employee.depId)
    employeeDS.flatMap(employeeDS => employeeDS.name.split(" "))
    employeeDS.mapPartitions(
      employees => {
        val result = scala.collection.mutable.ArrayBuffer[(String, Long)]()
        while (employees.hasNext) {
          val emp = employees.next()
          result += ((emp.name, emp.salary + 1000))
        }
        result.iterator
      })

    // join
    employeeDS.joinWith(employeeDS2, $"depId" === $"depId")

    // sort
    employeeDS.sort($"name".desc)
    employeeDS.sort(employeeDS("age").desc)
    employeeDS.sortWithinPartitions($"age")

    //randomSplit、sample
   // employeeDS.randomSplit()
    employeeDS.sample(0.9)
    employeeDS.select()
  }

}
