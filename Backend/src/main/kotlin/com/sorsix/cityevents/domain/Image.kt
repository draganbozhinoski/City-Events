package com.sorsix.cityevents.domain

import javax.persistence.*

@Entity
@javax.persistence.Table(name="city_images")
@Suppress
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1L,
    val name:String?,
    @Column(length=1000)
    val bytes:ByteArray,
    val type:String?
)
