package com.sorsix.cityevents.domain
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.Nullable
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    val logoUrl:String,
    val description:String,
    val covidCertificate:Boolean,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    val date:LocalDateTime,
    @ManyToOne
    @JsonManagedReference
    val locale:Locale
)
