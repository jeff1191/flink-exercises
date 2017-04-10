package com.kschool.streaming.datatype

import org.apache.flink.api.common.typeinfo.TypeInformation
/**
  * Created by Jeff on 05/04/2017.
  */
object Models {

  case class MeetupRSVGevent(var member: Member, var response: String, var visibility: String,var event: Event,
                             var mtime: String,var guests: String, var rsvp_id: String, var group: Group,var venue: Venue) extends Comparable[MeetupRSVGevent]{

    def this(){
      this(new Member, "","", new Event,"","","", new Group, new Venue)
    }
    override def compareTo(o: MeetupRSVGevent): Int = o.event.compareTo(event)
  }

  case class Member(var member_name: String,var photo: String,var member_id: String) extends Comparable[Member]{
    def this(){
      this("","","")
    }

    override def compareTo(o: Member): Int = { o.member_id.compareTo(member_id)}
  }

  case class Venue(var lon: String,var venue_name: String,var venue_id: String,var lat: String) extends Comparable[Venue]{
    def this(){
      this("","","","")
    }
    override def compareTo(o: Venue): Int = o.venue_name.compareTo(venue_name)
  }

  case class Group(var group_name: String,var group_city: String,var group_lat: String,var group_urlname: String,var group_id: String,
                   var group_country: String,var group_lon: String, var group_topics: java.util.Collection[Group_topics] ) extends Comparable[Group]{
    def this(){
      this("","","","","","","",  new java.util.ArrayList[Group_topics])
    }

    override def compareTo(o: Group): Int = o.group_id.compareTo(group_id)
  }

  case class Event(var time: String,var event_url: String,var event_id: String,var event_name: String) extends Comparable[Event]{
    def this(){
      this("", "", "", "")
    }

    override def compareTo(o: Event): Int = o.event_id.compareTo(event_id)
  }

  case class Group_topics(var urlkey: String,var topic_name: String) extends Comparable[Group_topics]{
    def this(){
      this("","")
    }
    override def compareTo(o: Group_topics): Int = o.urlkey.compareTo(urlkey)

    override def equals(obj: scala.Any): Boolean = super.equals(obj)
  }

}