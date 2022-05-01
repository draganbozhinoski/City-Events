package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.repository.ReservationsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.transaction.Transactional

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

    @Transactional
    fun deleteReservation(id: Long): ReservationResponse {
        return when(val reservation = reservationsRepository.findByIdOrNull(id)) {
            null -> ReservationError("We can't find that review in the database.")
            else -> {
                reservationsRepository.delete(reservation)
                ReservationSuccess(reservation)
            }
        }
    }
}