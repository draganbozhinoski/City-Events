package com.sorsix.cityevents.service

import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.repository.ReservationsRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ReservationService(val reservationsRepository: ReservationsRepository) {
    fun findAll():List<Reservation> {
        return reservationsRepository.findAll()
    }

    @Scheduled(cron = "0 0 4 * * ?")
    fun clearReservations() {
        val reservationsList = reservationsRepository.findAll()
        //TODO:

    }
}