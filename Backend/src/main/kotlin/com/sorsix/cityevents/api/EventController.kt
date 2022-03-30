package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.EventRequest
import com.sorsix.cityevents.api.responses.EventError
import com.sorsix.cityevents.api.responses.EventResponse
import com.sorsix.cityevents.api.responses.EventSuccess
import com.sorsix.cityevents.domain.Event
import com.sorsix.cityevents.service.EventsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(val service:EventsService) {
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
            service.save(name,numReservations,city,adult,covidCertificate,date,locale)
        }
    //update
    //delete
}