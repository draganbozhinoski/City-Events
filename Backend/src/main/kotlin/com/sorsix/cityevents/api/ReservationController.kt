package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.service.ReservationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reservations")
class ReservationController(val reservationService: ReservationService) {
    @GetMapping
    fun getAllReservations():List<Reservation> {
        return reservationService.findAll()
    }
}