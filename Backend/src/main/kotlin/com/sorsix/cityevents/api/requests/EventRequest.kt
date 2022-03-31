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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    val date:LocalDateTime,
    val localeId: Long
)