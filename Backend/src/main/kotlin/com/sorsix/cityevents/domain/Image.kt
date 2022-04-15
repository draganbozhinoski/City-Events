package com.sorsix.cityevents.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@javax.persistence.Table(name="city_images")
@Suppress
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val name:String?,
    val bytes:ByteArray,
    val type:String?
)
