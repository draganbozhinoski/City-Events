package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.service.EventsService
import com.sorsix.cityevents.service.LocaleService
import org.apache.coyote.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(val service:EventsService,val localeService:LocaleService) {
    val logger:Logger = LoggerFactory.getLogger("Events Logger")
    //read all
    @GetMapping
    fun getEvents():MutableList<Event> {
        return service.findAll()
    }
    //find by id
    @GetMapping("/{id}")
    fun getEventById(@PathVariable id:Long): ResponseEntity<EventResponse> {
        return when (val event = service.findById(id)) {
            is EventSuccess -> ResponseEntity.ok().body(event)
            is EventError -> ResponseEntity.badRequest().body(event)
        }
    }
    //create
    @PostMapping("/save")
    fun createEvent(@RequestBody eventRequest:EventRequest):ResponseEntity<LocaleResponse> =
        with(eventRequest){
            return when(val locale = localeService.getLocale(localeId)){
                is LocaleError -> {
                    logger.warn("Locale was not found with the id user requested")
                    ResponseEntity.badRequest().body(locale)
                }
                is LocaleSuccess -> {
                    service.save(name,numReservations,city,adult,covidCertificate,date,locale.locale)
                    logger.info("Event object saved in the database")
                    ResponseEntity.ok().body(locale)
                }
            }
        }
    //update
    @PutMapping("/update/{id}")
    fun updateEvent(@PathVariable id:Long,
                    @RequestBody eventRequest: EventRequest) {
        //TODO:
    }
    //delete
    @DeleteMapping("/delete/{id}")
    fun deleteEvent(@PathVariable id:Long) {
        service.deleteById(id)
    }
}