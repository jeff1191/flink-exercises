package com.kschool.streaming

import com.kschool.streaming.websocketsource.MeetupStreamingSource
import com.kschool.streaming.datatype.Models.MeetupRSVGevent
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
/**
  * Created by Jeff on 05/04/2017.
  */
object StreamingOne extends App{
  val env = StreamExecutionEnvironment.createLocalEnvironment(1)
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))
  //Eliminar los objetos mal formados (alguno de sus campos a null)
  films.filter(x =>
    (x.event != null &&  x.group != null && x.guests != null && x.member != null && x.mtime != null &&
            x.response != null && x.rsvp_id != null &&  x.venue != null && x.visibility != null)
  ).print()
  env.execute("Streaming, Exercise One")
}
