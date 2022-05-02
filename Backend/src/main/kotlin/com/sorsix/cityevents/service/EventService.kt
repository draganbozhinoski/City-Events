package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.EventError
import com.sorsix.cityevents.api.responses.EventResponse
import com.sorsix.cityevents.api.responses.EventSuccess
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Image
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.repository.EventImagesRepository
import com.sorsix.cityevents.repository.EventsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class EventService(private val eventsRepository: EventsRepository, val eventImagesRepository: EventImagesRepository) {

    fun findAll(): List<Event> {
        return eventsRepository.findAll()
    }

    fun findById(id: Long): EventResponse {
        return when (val event = eventsRepository.findByIdOrNull(id)) {
            null -> EventError("We can't find event with given id in the database")
            else -> EventSuccess(event)
        }
    }

    fun save(
        name: String,
        numReservations: Int,
        city: String,
        adult: Boolean,
        covidCertificate: Boolean,
        date: LocalDateTime,
        locale: Locale,
        logoUrl: String,
        description: String,
        image: Image
    ) {
        eventsRepository.save(
            Event(
                name = name,
                numReservations = numReservations,
                city = city,
                adult = adult,
                covidCertificate = covidCertificate,
                date = date,
                locale = locale,
                logoUrl = logoUrl,
                description = description,
                image = image
            )
        )
    }

    @Transactional
    fun update(
        id: Long,
        name: String,
        numReservations: Int,
        city: String,
        adult: Boolean,
        covidCertificate: Boolean,
        date: LocalDateTime,
        locale: Locale,
        logoUrl: String,
        description: String
    )
            : EventResponse {
        eventsRepository.updateEvent(id, name, numReservations, city, adult, covidCertificate, date, locale, logoUrl)
        return EventSuccess(eventsRepository.findByIdOrNull(id)!!)
    }

    @Transactional
    fun deleteById(id: Long) {
        eventsRepository.deleteById(id)
    }

}