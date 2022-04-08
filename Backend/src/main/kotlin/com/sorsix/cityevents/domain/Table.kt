package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_tables")
data class Table(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1,
    val reserved:Boolean,
    @OneToOne(mappedBy = "table")
    @JsonManagedReference
    val reservation: Reservation?,
    @ManyToOne
    @JsonBackReference
    val locale:Locale?
)
