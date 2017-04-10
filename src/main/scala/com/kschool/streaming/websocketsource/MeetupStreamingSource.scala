package com.kschool.streaming.websocketsource

import com.google.gson.Gson
import com.kschool.streaming.datatype.Models.{Event, MeetupRSVGevent}
import org.apache.flink.streaming.api.functions.source.RichSourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.slf4j.LoggerFactory
import org.apache.flink.streaming.api.scala._

import scalawebsocket.WebSocket

/**
  * Created by Jeff on 05/04/2017.
  */
class MeetupStreamingSource(uri: String) extends RichSourceFunction[MeetupRSVGevent] {

  protected[this] var running = true

  override def cancel(): Unit = {
    running = false
  }

  override def run(sourceContext: SourceContext[MeetupRSVGevent]): Unit = {
    while (running) {
      Thread.sleep(5000)
      WebSocket().open(uri).onTextMessage(msg => sourceContext.collect(toMeetupRSVGevent(msg)))
    }
    running = false
  }

  private def toMeetupRSVGevent(msg: String): MeetupRSVGevent ={// println(msg)
    new Gson().fromJson(msg,classOf[MeetupRSVGevent])
  }
}
