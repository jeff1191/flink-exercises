package com.kschool.batch

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
/**
  * Created by Jeff on 04/04/2017.
  */
object ExerciseOne extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)

  //Media de nominaciones de las películas
  val exer1 = films.map( x =>if (x.nominations.equalsIgnoreCase("-")) 0 else Integer.parseInt(x.nominations))
    .reduce((n1:Int, n2: Int) =>  n1 + n2)
  val nFilms:Long = films.count()
  println (s"Nominations mean: ${exer1.collect()(0)/nFilms}")
}
