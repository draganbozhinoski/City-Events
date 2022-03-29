package com.sorsix.cityevents.domain

import com.sorsix.cityevents.domain.enums.LocaleType
import javax.persistence.*

@Entity
@javax.persistence.Table(name="city_locales")
data class Locale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String,
    @Enumerated(EnumType.STRING)
    val type:LocaleType,
    @OneToMany(mappedBy = "locale", fetch = FetchType.LAZY)
    val tablesList:List<Table>,
    @OneToMany(mappedBy = "locale")
    val reservationsList:List<Reservation>,
    @OneToMany(mappedBy = "locale", fetch = FetchType.LAZY)
    val eventsList:List<Event>,
    @OneToMany(mappedBy = "locale", fetch = FetchType.LAZY)
    val reviewsList:List<Review>
)