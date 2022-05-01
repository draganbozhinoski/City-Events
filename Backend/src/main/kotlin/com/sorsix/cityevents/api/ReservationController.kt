package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")
class ReservationController(val reservationService: ReservationService) {
    @GetMapping
    fun getAllReservations():List<Reservation> {
        return reservationService.findAll()
    }

    @DeleteMapping("/delete/{id}")
    fun deleteReservation(@PathVariable id:Long): ResponseEntity<List<Reservation>> {
        return when(val reservation: ReservationResponse = reservationService.deleteReservation(id)) {
            is ReservationSuccess -> {
                ResponseEntity.ok().body(reservationService.findAll())
            }
            is ReservationError -> {
                ResponseEntity.badRequest().body(reservationService.findAll())
            }
        }
    }
}