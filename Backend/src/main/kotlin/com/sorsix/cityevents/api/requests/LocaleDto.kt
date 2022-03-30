package com.sorsix.cityevents.api.requests

import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.domain.Table
import com.sorsix.cityevents.domain.enums.LocaleType

data class LocaleDto(
    val id: Long,
    val name:String,
    val type: LocaleType,
    val tablesList:List<Table>,
    val reservationList:List<Reservation>,
    val eventsList:List<Event>,
    val reviewsList:List<Review>)