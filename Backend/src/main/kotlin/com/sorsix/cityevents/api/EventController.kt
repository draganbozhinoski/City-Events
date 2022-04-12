package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.service.EventService
import com.sorsix.cityevents.service.LocaleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("/api/events")
class EventController(val eventService:EventService, val localeService:LocaleService) {
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
                    eventService.save(name,numReservations,city,adult,covidCertificate,date,locale.locale,logoUrl,description)
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
                            eventService.update(id,name,numReservations,city,adult,covidCertificate,date,locale.locale,logoUrl,description)
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
    //delete event by given id
    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id:Long) :ResponseEntity<EventResponse> {
        return when(val event = eventService.findById(id)) {
            is EventSuccess -> {
                logger.info("Event was deleted successfully.")
                eventService.deleteById(id)
                ResponseEntity.ok().body(event)
            }
            is EventError -> {
                logger.error(event.errorMessage)
                ResponseEntity.badRequest().body(event)
            }
        }
    }
    //delete all events by given locale id
    @DeleteMapping("/delete/locale/{id}")
    fun deleteAllByLocaleId(@PathVariable id:Long):ResponseEntity<LocaleResponse> {
        return when(val locale = localeService.getLocale(id)) {
            is LocaleSuccess -> {
                logger.info("Locale found, attempting to delete events")
                locale.locale.eventsList.clear()
                logger.info("Events deleted from locale id $id")
                ResponseEntity.ok().body(locale)
            }
            is LocaleError -> {
                logger.error("Locale with id $id was not found in the database.")
                ResponseEntity.badRequest().body(locale)
            }
        }
    }

    //return locale with given event id
    @GetMapping("/{id}/locale")
    fun getLocaleByEventId(@PathVariable id:Long):ResponseEntity<Any> {
        return when(val event = eventService.findById(id)) {
            is EventSuccess -> {
                ResponseEntity.ok().body(event.event.locale)
            }
            is EventError -> {
                ResponseEntity.badRequest().body(event)
            }
        }
    }



}