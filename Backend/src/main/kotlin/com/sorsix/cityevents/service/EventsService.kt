package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.EventResponse
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Locale
import java.time.LocalDateTime

interface EventsService {
    fun findAll():MutableList<Event>
    fun findById(id:Long):EventResponse
    fun save(name:String,numReservations:Int,city:String,adult:Boolean,covidCertificate:Boolean,date:LocalDateTime,locale:Locale?)
}