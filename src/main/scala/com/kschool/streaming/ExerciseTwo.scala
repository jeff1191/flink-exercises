package com.kschool.streaming

import java.io.Serializable
import java.lang.Iterable
import java.time.LocalDate

import akka.remote.serialization.ProtobufSerializer
import com.esotericsoftware.kryo.{Kryo, Serializer}
import com.kschool.streaming.websocketsource.Models.{Event, MeetupRSVGevent}
import org.apache.flink.streaming.api.scala._
import com.kschool.streaming.websocketsource.MeetupStreamingSource
import org.apache.flink.api.common.functions.{FilterFunction, MapFunction}
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.datastream.{DataStream, DataStreamSource, SingleOutputStreamOperator}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.windowing.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.codehaus.jackson.map.ext.JodaSerializers.LocalDateSerializer
/**
  * Created by Jeff on 05/04/2017.
  */
object ExerciseTwo  {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig().setParallelism(1)

    val stringInfo: TypeInformation[Event] = createTypeInformation[Event]
    val url = "wss://stream.meetup.com/2/rsvps"

    val films: DataStreamSource[Event] = env.addSource(new MeetupStreamingSource(url))
    //  Contar los usuarios que han confirmado a cada evento en los  últimos 10 segundos
   // val filtro = films.keyBy("event")
   //   .timeWindow(Time.seconds(10)).apply {
     // new MyWindowExerciseTwo()
    //}
case class Parcin(param1: String, paramdos: String)

   val datap =films.filter(new FilterFunction[Event] {
      override def filter(t: Event): Boolean = {
        (t != null && t.event_id != null && t.event_name != null && t.event_url != null && t.time != null)}
      }).keyBy("event_id")

    env.execute("Streaming, Exercise Two")
}
//IN, OUT, K, Time
class MyWindowExerciseTwo extends WindowFunction[MeetupRSVGevent,(String, Int),Tuple,TimeWindow] {
  override def apply(key: Tuple, w: TimeWindow, input: Iterable[MeetupRSVGevent], out: Collector[(String, Int)]): Unit = {
    var count = 0
    val it = input.iterator()
    while (it.hasNext) {
      count = count + 1
    }
    out.collect("# Confirmed users:",count )
  }
}

}
