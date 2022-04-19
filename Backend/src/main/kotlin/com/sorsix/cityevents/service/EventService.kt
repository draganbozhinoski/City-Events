package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.EventResponse
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Image
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.view.EventImage
import java.time.LocalDateTime

interface EventService {
    fun findAll():List<Event>
    fun findById(id:Long):EventResponse
    fun save(name:String,numReservations:Int,city:String,adult:Boolean,covidCertificate:Boolean,date:LocalDateTime,locale:Locale,logoUrl:String,description:String,image:Image)
    fun update(id:Long,name:String,numReservations:Int,city:String,adult:Boolean,covidCertificate:Boolean,date:LocalDateTime,locale:Locale,logoUrl:String,description:String):EventResponse
    fun deleteById(id:Long)
}