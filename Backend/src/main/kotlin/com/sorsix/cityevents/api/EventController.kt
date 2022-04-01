package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.service.EventsService
import com.sorsix.cityevents.service.LocaleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("/api/events")
class EventController(val service:EventsService,val localeService:LocaleService) {
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
    fun createEvent(@RequestBody eventRequest:EventRequest) =
        with(eventRequest){
            when(val locale = localeService.getLocale(localeId)){
                is LocaleError -> ResponseEntity.badRequest().body(locale)
                is LocaleSuccess -> {
                    service.save(name,numReservations,city,adult,covidCertificate,date,locale.locale)
                    ResponseEntity.ok().body(locale)
                }
            }

        }
    //update
    //delete
}