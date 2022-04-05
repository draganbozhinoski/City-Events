package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Locale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface EventsRepository: JpaRepository<Event, Long> {
    @Modifying
    @Query("update Event e set e.name=:name,e.numReservations=:numReservations,e.city=:city,e.adult=:adult,e.covidCertificate=:covidCertificate,e.date=:date,e.locale=:locale where e.id=:id")
    fun updateEvent(id: Long, name: String, numReservations: Int, city: String, adult: Boolean, covidCertificate: Boolean, date: LocalDateTime, locale: Locale)
}