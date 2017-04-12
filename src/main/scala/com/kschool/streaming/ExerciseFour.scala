package com.kschool.streaming

import com.kschool.streaming.websocketsource.MeetupStreamingSource
import com.kschool.streaming.datatype.Models.MeetupRSVGevent
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector
/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseFour extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))
//  Contar los usuarios por países cada 5 segundos
  films.filter(x => x.group.group_country != null).keyBy("group.group_country").timeWindow(Time.seconds(5)).
    apply((key, window, input, out: Collector[(String, Int)]) => {
      out.collect(s"Country: ${key} ", input.size )
    }).print()

  env.execute("Streaming, Exercise Four")
}
