package com.sorsix.cityevents.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@javax.persistence.Table(name = "city_reservations")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String,
    val phoneNumber:String,
    val from:LocalDateTime,
    @OneToOne
    val table:Table,
    @ManyToOne
    val locale:Locale,
    @ManyToOne
    val user:User

)