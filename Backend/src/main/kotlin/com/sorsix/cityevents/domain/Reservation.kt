package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.Nullable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@javax.persistence.Table(name = "city_reservations")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val name:String,
    val description:String, // separe, gore levo, dole desno, do sank ..
    val phoneNumber:String,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    val dateTime:LocalDateTime,
    @OneToOne
    @JsonBackReference
    val table:Table,
    @ManyToOne
    @JsonBackReference
    val locale:Locale,
    @ManyToOne
    @JsonBackReference
    val user:User? // TODO:ne smee da e null,samo za testing e null
)