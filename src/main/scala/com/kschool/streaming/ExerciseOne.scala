package com.kschool.streaming

import com.google.gson.Gson
import com.kschool.batch.Film
import com.kschool.streaming.websocketsource.{Event, MeetupRSVGevent, MeetupStreamingSource}
import org.apache.flink.api.common.functions.{FilterFunction, MapFunction}
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

import scalawebsocket.WebSocket

/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseOne extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))

  env.execute("Streaming, Exercise One")
}
