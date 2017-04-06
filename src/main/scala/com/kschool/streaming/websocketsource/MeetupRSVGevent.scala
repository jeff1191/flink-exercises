package com.kschool.streaming.websocketsource

import scala.beans.BeanProperty


/**
  * Created by Jeff on 05/04/2017.
  */
case class MeetupRSVGevent (  member: Member,  response: String, visibility: String,  event: Event,  mtime: String,  guests: String,  rsvp_id: String,  group: Group,  venue: Venue)
case class Member(  member_name: String,  photo: String,  member_id: String)
case class Venue(  lon: String,  venue_name: String,  venue_id: String , lat: String)
case class Group(  group_name: String,  group_city: String,  group_lat: String,  group_urlname: String,  group_id: String,  group_country: String,  group_lon: String,  group_topics: Array[Group_topics] with Serializable)
case class Event(  time: String, event_url: String , event_id: String , event_name: String)
case class Group_topics(  urlkey: String, topic_name: String)