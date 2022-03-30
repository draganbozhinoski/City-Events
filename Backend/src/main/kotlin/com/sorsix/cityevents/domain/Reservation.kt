package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
    @JsonBackReference
    val locale:Locale,
    @ManyToOne
    @JsonBackReference
    val user:User
)