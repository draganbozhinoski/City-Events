package com.sorsix.cityevents.api.requests

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.User
import java.time.LocalDateTime

data class ReservationRequest(
    val name:String,
    val phoneNumber:String,
    val dateTime:LocalDateTime,
    // sega za sega kje ostane na prvata slobodna masa vo ciklusot od lokalot.
    val username:String,
    val description:String
)
