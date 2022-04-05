package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sorsix.cityevents.domain.enums.LocaleType
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val tablesList:MutableList<com.sorsix.cityevents.domain.Table> = mutableListOf(),

    @OneToMany(mappedBy = "locale")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val reservationsList:MutableList<Reservation> = mutableListOf(),

    @OneToMany(mappedBy = "locale")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val eventsList:MutableList<Event> = mutableListOf(),

    @OneToMany(mappedBy = "locale")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    val reviewsList:MutableList<Review> = mutableListOf()
)