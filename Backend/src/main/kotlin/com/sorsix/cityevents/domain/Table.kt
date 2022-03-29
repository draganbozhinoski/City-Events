package com.sorsix.cityevents.domain

import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_tables")
data class Table(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val reserved:Boolean,
    @OneToOne
    val reservation: Reservation,
    @ManyToOne
    val locale:Locale
)
