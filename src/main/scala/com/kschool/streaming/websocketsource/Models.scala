package com.kschool.streaming.websocketsource

import scala.beans.BeanProperty


/**
  * Created by Jeff on 05/04/2017.
  */
object Models {

  case class MeetupRSVGevent(member: Member, response: String, visibility: String, event: Event, mtime: String, guests: String, rsvp_id: String, group: Group, venue: Venue) extends Comparable[MeetupRSVGevent]{

    def this(){
      this(new Member, "","", new Event,"","","", new Group, new Venue)
    }

    override def compareTo(o: MeetupRSVGevent): Int = o.event.event_name.compareTo(event.event_name)
  }

  case class Member(member_name: String, photo: String, member_id: String) extends Comparable[Member]{
    def this(){
      this("","","")
    }

    override def compareTo(o: Member): Int = { o.member_name.compareTo(member_name)}
  }

  case class Venue(lon: String, venue_name: String, venue_id: String, lat: String){
    def this(){
      this("","","","")
    }
  }

  case class Group(group_name: String, group_city: String, group_lat: String, group_urlname: String, group_id: String,
                   group_country: String, group_lon: String, group_topics: Array[Group_topics] ){
    def this(){
      this("","","","","","","", new Array[Group_topics](0))
    }
  }

  case class Event(time: String, event_url: String, event_id: String, event_name: String) extends Comparable[Event]{
    def this(){
      this("", "", "", "")
    }

    override def compareTo(o: Event): Int = o.event_name.compareTo(event_name)
  }

  case class Group_topics(urlkey: String, topic_name: String){
    def this(){
      this("","")
    }
  }

}