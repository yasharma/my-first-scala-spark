package com.example
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object MyFirstScalaSpark {
  def main(args: Array[String]) {
    val spark = SparkSession.builder
      .appName("MyFirstScalaSpark")
      .getOrCreate()
    val filePath = args(0)
    val data = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .option("timestampFormat", "dd/MM/yyyy")
      .csv(filePath)
    
    val orderedData = data.orderBy(desc("Date of Payment"))
    orderedData.printSchema
    orderedData.show  
    
    println(data.count)
    spark.stop()
  }
}