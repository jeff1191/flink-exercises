package com.kschool.batch

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector
/**
  * Created by Jeff on 04/04/2017.
  */
object ExerciseSix extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)

  //¿Cuál es la desviación estándar del rating de las películas ganadoras en el siglo XXI?
  // formula used to calculate the standard deviation
  //          (       n * Sum(x)^2 - (Sum(x))^2)    )
  // s =  sqrt(-------------------------------------)
  //          (              ( n - 1 )              )
  val exer6 = films.map(x => (x.year.substring(0,2), x.rating.toDouble)).filter(x => x._1.equalsIgnoreCase("20"))
    .groupBy(0).reduceGroup{
    ( tuple, out: Collector[(String, Double)]) =>
      var key: String  = ""
      var n = 0
      var sum: Double = 0
      var sum2: Double = 0
      for(pair <- tuple){
        key = pair._1 + "XX"
        sum += pair._2
        sum2 += (pair._2 * pair._2)
        n += 1
      }
      val variance:Double = ((n * sum2) - (sum * sum)) / (n *( n - 1 ) )
      out.collect(key, Math.sqrt(variance))
  }
  exer6.map(x => "Period: "+ x._1 +" Std.Dev :" + x._2).print()
}
