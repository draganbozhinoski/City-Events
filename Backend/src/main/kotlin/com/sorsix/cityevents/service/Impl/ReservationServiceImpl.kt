package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.repository.ReservationsRepository
import com.sorsix.cityevents.service.ReservationService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ReservationServiceImpl(val reservationsRepository: ReservationsRepository):ReservationService {
    override fun findAll():List<Reservation> {
        return reservationsRepository.findAll()
    }

    @Scheduled(cron = "0 0 4 * * ?")
    override fun clearReservations() {
        val reservationsList = reservationsRepository.findAll()

    }
}