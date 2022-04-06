package com.sorsix.cityevents.service

import com.sorsix.cityevents.domain.Reservation

interface ReservationService {
    fun findAll():List<Reservation>
    fun clearReservations()
}