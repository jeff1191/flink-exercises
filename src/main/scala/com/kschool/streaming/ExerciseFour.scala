package com.kschool.streaming

import com.kschool.streaming.websocketsource.{MeetupRSVGevent, MeetupStreamingSource}
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseFour extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))

  env.execute("Streaming, Exercise Four")
}
