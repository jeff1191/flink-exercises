package com.kschool.batch

import com.kschool.batch.datatype.Film
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import org.codehaus.jackson.map.ObjectMapper
/**
  * Created by Jeff on 04/04/2017.
  */
object BatchFive extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)

  // ¿Cuántas películas ganadoras incluyen todas las palabras de su título en la sinopsis?
  //films.filter(x => x.synopsis.contains(x.name)).print()
  println(films.filter(x => x.synopsis.contains(x.name)).count)

}
