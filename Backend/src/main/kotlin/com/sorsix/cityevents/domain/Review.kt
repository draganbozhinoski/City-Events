package com.sorsix.cityevents.domain

import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_reviews")
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val review:String,
    val stars:Int,
    @ManyToOne
    val locale:Locale
)
