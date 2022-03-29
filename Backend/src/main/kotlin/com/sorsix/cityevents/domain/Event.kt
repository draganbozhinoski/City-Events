package com.sorsix.cityevents.domain

import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String,
    val numReservations:Int,
    val city:String,
    val adult:Boolean,
    val covidCertificate:Boolean,
    val date:LocalDateTime,
    @ManyToOne
    val locale:Locale
)
