package com.kschool.batch

import com.kschool.batch.datatype.Film
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
/**
  * Created by Jeff on 04/04/2017.
  */
object BatchFour extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)
  //¿Cuántas películas ganadoras incluyen al menos una de las palabras de su título en la sinopsis?
  val exer4 = films.filter{x =>
    var count = 0
    for ( word <- x.name.split(" "))
      count += word.r.findAllIn(x.synopsis).length
    count != 0
  }
 // exer4.map(x => x.name + "-> "+ x.synopsis).print()
  println(exer4.map(x => x.name + "-> "+ x.synopsis).count())
}
