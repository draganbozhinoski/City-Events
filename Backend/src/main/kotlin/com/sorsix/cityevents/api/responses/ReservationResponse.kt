package com.sorsix.cityevents.api.responses

import com.sorsix.cityevents.domain.Reservation

sealed interface ReservationResponse

data class ReservationSuccess(val reservation:Reservation): ReservationResponse
data class ReservationError(val errorMessage:String): ReservationResponse