package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationsRepository: JpaRepository<Reservation,Long> {
}