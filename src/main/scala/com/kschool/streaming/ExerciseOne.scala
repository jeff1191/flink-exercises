package com.kschool.streaming

import com.kschool.streaming.websocketsource.{Event, MeetupRSVGevent, MeetupStreamingSource}
import org.apache.flink.api.common.functions.{FilterFunction, MapFunction}
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseOne extends App{
  val  env = StreamExecutionEnvironment.getExecutionEnvironment
  val url = "wss://stream.meetup.com/2/rsvps"

//  val films:DataStream[MeetupRSVGevent]  = env.addSource(new MeetupStreamingSource(url))
//  //Eliminar los objetos mal formados (alguno de sus campos a null)
//  films.filter(new FilterFunction[MeetupRSVGevent] {
//    override def filter(meetupRSVGevent: MeetupRSVGevent): Boolean = {
//      (meetupRSVGevent.event != null &&  meetupRSVGevent.group != null &&
//      meetupRSVGevent.guests != null &&
//      meetupRSVGevent.member != null &&
//      meetupRSVGevent.mtime != null &&
//      meetupRSVGevent.response != null &&
//      meetupRSVGevent.rsvp_id != null &&
//      meetupRSVGevent.venue != null &&
//      meetupRSVGevent.visibility != null)
//  }
//  }).print()

  env.execute("Streaming, Exercise One")
}
