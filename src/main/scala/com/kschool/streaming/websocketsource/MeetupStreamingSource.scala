package com.kschool.streaming.websocketsource

import com.google.gson.Gson
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.codehaus.jackson.map.{DeserializationConfig, ObjectMapper}
import org.slf4j.LoggerFactory

import scalawebsocket.WebSocket

/**
  * Created by Jeff on 05/04/2017.
  */
class MeetupStreamingSource(uri: String) extends SourceFunction[MeetupRSVGevent] {

  protected[this] var running = true

  override def cancel(): Unit = {
    running = false
  }

  override def run(sourceContext: SourceContext[MeetupRSVGevent]): Unit = {
    while (running) {
      Thread.sleep(2000)
      WebSocket().open(uri).onTextMessage(msg => sourceContext.collect(toMeetupRSVGevent(msg)))
    }
    running = false
  }

  private def toMeetupRSVGevent(msg: String): MeetupRSVGevent ={
    new Gson().fromJson(msg,classOf[MeetupRSVGevent])
  }
}
