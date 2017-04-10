package com.kschool.streaming

import com.kschool.streaming.websocketsource.MeetupStreamingSource
import com.kschool.streaming.datatype.Models.{Group_topics, MeetupRSVGevent}
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, KeySelectorWithType, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseFive extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))
//  Calcular los Trending Topics (palabras semánticamente significativas más repetidos de los topic_name) teniendo en
//  cuenta la información del último minuto y actualizando el  resultado cada 10 segundos
  films.filter((x => x.group.group_topics.size() > 0 )).keyBy("group.group_topics.topic_name")
  env.execute("Streaming, Exercise Five")
}
