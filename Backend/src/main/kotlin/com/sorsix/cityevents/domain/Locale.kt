package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sorsix.cityevents.domain.enums.LocaleType
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_locales")
data class Locale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val name:String,
    @Enumerated(EnumType.STRING)
    val type:LocaleType,
    @OneToMany(mappedBy = "locale")
    @JsonBackReference
    val tablesList:MutableList<com.sorsix.cityevents.domain.Table> = mutableListOf(),
    @OneToMany(mappedBy = "locale")
    @JsonBackReference
    val reservationsList:MutableList<Reservation> = mutableListOf(),
    @OneToMany(mappedBy = "locale")
    @JsonBackReference
    val eventsList:MutableList<Event> = mutableListOf(),
    @OneToMany(mappedBy = "locale")
    @JsonBackReference
    val reviewsList:MutableList<Review> = mutableListOf()
)