package com.sorsix.cityevents.api.requests

import com.fasterxml.jackson.annotation.JsonFormat
import com.sorsix.cityevents.domain.Locale
import com.sun.istack.Nullable
import java.time.LocalDateTime
import javax.persistence.ManyToOne

data class EventRequest(
    val name:String,
    val numReservations:Int,
    val city:String,
    val adult:Boolean,
    val covidCertificate:Boolean,
    val date:LocalDateTime,
    val localeId: Long,
    val description:String,
    val logoUrl:String,
    val imageId:Long
)