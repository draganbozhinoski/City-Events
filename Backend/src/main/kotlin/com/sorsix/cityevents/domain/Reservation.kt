package com.sorsix.cityevents.domain

import com.sun.istack.Nullable
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
    val datetime:LocalDateTime,
    @OneToOne(mappedBy = "reservation")
    val table:Table,
    @ManyToOne
    val locale:Locale,
    @ManyToOne
    val user:User
)