package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.repository.EventsRepository
import com.sorsix.cityevents.repository.LocaleRepository
import com.sorsix.cityevents.service.EventsService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EventsServiceImpl(private val eventsRepository: EventsRepository,private val localeRepository:LocaleRepository) :EventsService {

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

    override fun deleteById(id: Long) {
        eventsRepository.deleteById(id)
    }

}