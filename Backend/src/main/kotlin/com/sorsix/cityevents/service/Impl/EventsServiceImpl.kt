package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.api.responses.EventError
import com.sorsix.cityevents.api.responses.EventResponse
import com.sorsix.cityevents.api.responses.EventSuccess
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.repository.EventsRepository
import com.sorsix.cityevents.service.EventsService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EventsServiceImpl(private val eventsRepository: EventsRepository) :EventsService {

    override fun findAll():MutableList<Event> {
        return eventsRepository.findAll()
    }

    override fun findById(id: Long): EventResponse {
        return when(val event = eventsRepository.findByIdOrNull(id)){
            null -> EventError("We can't find event with given id in the database")
            else -> EventSuccess(event)
        }
    }

    override fun save(
        name: String,
        numReservations: Int,
        city: String,
        adult: Boolean,
        covidCertificate: Boolean,
        date: LocalDateTime,
        locale: Locale
    ) {
        eventsRepository.save(Event(name = name, numReservations = numReservations, city = city,adult=adult, covidCertificate = covidCertificate, date = date,locale=locale))
    }
}