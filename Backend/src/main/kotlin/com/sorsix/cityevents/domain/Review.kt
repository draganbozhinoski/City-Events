package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_reviews")
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val review:String,
    val stars:Int,
    @ManyToOne
    @JsonManagedReference
    val locale:Locale
)
