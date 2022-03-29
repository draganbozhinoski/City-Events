package com.sorsix.cityevents.domain

import com.sorsix.cityevents.domain.enums.UserType
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String,
    @Enumerated(EnumType.STRING)
    val type:UserType,
    @OneToOne
    val localeManages:Locale?,
    @OneToMany(mappedBy = "user")
    val reservation:List<Reservation>?
)
