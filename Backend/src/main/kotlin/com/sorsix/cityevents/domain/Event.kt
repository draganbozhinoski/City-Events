package com.sorsix.cityevents.domain
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.Nullable
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val name:String,
    val numReservations:Int,
    val city:String,
    val adult:Boolean,
    val covidCertificate:Boolean,
    val date:LocalDateTime,
    @ManyToOne
    @JsonBackReference
    val locale:Locale
)
