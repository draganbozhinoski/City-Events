package com.sorsix.cityevents

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CityEventsApplication

fun main(args: Array<String>) {
    runApplication<CityEventsApplication>(*args)
}
