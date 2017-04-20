package com.kschool.batch

import com.kschool.batch.datatype.Film
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector
/**
  * Created by Jeff on 04/04/2017.
  */
object BatchThree extends App{
  // set up the execution environment
  val env = ExecutionEnvironment.getExecutionEnvironment
  // get input data
  val films:DataSet[Film] = env.readCsvFile[Film]("src/main/resources/pictures.csv",ignoreFirstLine = true)
  //Duración media de las películas ganadoras por décadas
  val exer3 = films.map(x => (x.year.substring(0,3), x.duration.toInt)).groupBy(0)
    .reduceGroup{
      ( tuple, out: Collector[(String, Double)]) =>
        var key: String = ""
        var count = 0
        var sum = 0
        for(pair <- tuple){
          key = pair._1 + "X"
          sum += pair._2
          count += 1
        }
        out.collect(key, sum/count)
    }
  exer3.map(x => "Decade: " + x._1 + " | Mean films duration: " + x._2).print()
}
