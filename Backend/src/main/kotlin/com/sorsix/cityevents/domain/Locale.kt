package com.sorsix.cityevents.domain

import com.sorsix.cityevents.domain.enums.LocaleType
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_locales")
data class Locale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String,
    @Enumerated(EnumType.STRING)
    val type:LocaleType,
    @OneToMany(mappedBy = "locale")
    val tablesList:List<com.sorsix.cityevents.domain.Table>,
    @OneToMany(mappedBy = "locale")
    val reservationsList:List<Reservation>,
    @OneToMany(mappedBy = "locale")
    val eventsList:List<Event>,
    @OneToMany(mappedBy = "locale")
    val reviewsList:List<Review>
)