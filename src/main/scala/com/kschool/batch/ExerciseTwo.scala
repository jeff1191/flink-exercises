package com.kschool.batch

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.util.Collector
import org.apache.flink.api.scala._
/**
  * Created by Jeff on 04/04/2017.
  */
object ExerciseTwo extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)
  //Media en Metacritic, agrupado por géneros, de las películas
  val exer2 = films.groupBy("genre1","genre2").reduceGroup{
    (films, out: Collector[(String, Double)]) =>
      var key: String = null
      var count = 0
      var sum = 0
      for(film <- films){
        key = film.genre1 + "/" + film.genre2
        sum = if (film.metacritic.equalsIgnoreCase("")) 0 + sum else Integer.parseInt(film.metacritic) + sum
        count += 1
      }
      out.collect(key, sum/count)
  }
  exer2.map(x => "Genre: " + x._1 + " | Mean: " + x._2).print
}
