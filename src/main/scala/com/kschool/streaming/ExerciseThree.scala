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
object ExerciseThree extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))
//Contar los usuarios que han confirmado a cada evento en los
//  últimos 20 segundos actualizando el resultado cada 5 segundos
  films.filter((x => x.response!= null && x.response.equalsIgnoreCase("YES"))).keyBy("event").
    timeWindow(Time.seconds(20), Time.seconds(5)). //sliding time window
    apply((key, window, input, out: Collector[(String, Int)]) => {
      out.collect(s"Event: ${key.getField(3)}, url: ${key.getField(1)} ", input.size )
    }).print()
  env.execute("Streaming, Exercise Three")
}
