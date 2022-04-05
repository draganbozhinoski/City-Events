package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.service.EventsService
import com.sorsix.cityevents.service.LocaleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("/api/events")
class EventController(val eventService:EventsService,val localeService:LocaleService) {
    val logger: Logger = LoggerFactory.getLogger("Event controller")
    //read all
    @GetMapping
    fun getEvents():MutableList<Event> = eventService.findAll()
    //find by id
    @GetMapping("/{id}")
    fun getEventById(@PathVariable id:Long): ResponseEntity<EventResponse> = when (val event = eventService.findById(id)) {
            is EventSuccess -> ResponseEntity.ok().body(event)
            is EventError -> ResponseEntity.badRequest().body(event)
        }
    //create
    @PostMapping("/save")
    fun createEvent(@RequestBody eventRequest:EventRequest) =
        with(eventRequest){
            when(val locale = localeService.getLocale(localeId)){
                is LocaleError -> {
                    logger.error("Locale with that id was not found.")
                    ResponseEntity.badRequest().body(locale)
                }
                is LocaleSuccess -> {
                    eventService.save(name,numReservations,city,adult,covidCertificate,date,locale.locale)
                    logger.info("Event successfully saved.")
                    ResponseEntity.ok().body(locale)
                }
            }

        }
    //update
    @PutMapping("/update/{id}")
    fun updateEvent(@RequestBody eventRequest:EventRequest,@PathVariable id:Long):ResponseEntity<Any> {
        with(eventRequest) {
            return when(val event = eventService.findById(id)) {
                is EventSuccess -> {
                    return when(val locale = localeService.getLocale(localeId)) {
                        is LocaleSuccess -> {
                            eventService.update(id,name,numReservations,city,adult,covidCertificate,date,locale.locale)
                            logger.info("Event successfully updated.")
                            ResponseEntity.ok().body(event)
                        }
                        is LocaleError -> {
                            logger.error("Locale with that id was not found.")
                            ResponseEntity.badRequest().body(locale)
                        }
                    }
                }
                is EventError -> {
                    logger.error("Event with that id was not found.")
                    ResponseEntity.badRequest().body(event)
                }
            }
        }
    }
    //delete
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id:Long) :ResponseEntity<EventResponse> {
        return when(val event = eventService.findById(id)) {
            is EventSuccess -> {
                logger.info("Event was deleted successfully.")
                ResponseEntity.ok().body(event)
            }
            is EventError -> {
                logger.error(event.errorMessage)
                ResponseEntity.badRequest().body(event)
            }
        }
    }
    //delete all events by given locale
    @DeleteMapping("/delete/locale/{id}")
    fun deleteAllByLocaleId(@PathVariable id:Long) {


    }

}