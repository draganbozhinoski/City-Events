package com.sorsix.cityevents.api.requests

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.User
import java.time.LocalDateTime

data class ReservationRequest(
    val name:String,
    val phoneNumber:Long,
    val dateTime:LocalDateTime,
    val username:String,
    val description:String
)
