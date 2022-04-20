package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sorsix.cityevents.domain.enums.UserType
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = -1,
    val username:String,
    val name:String,
    val email:String,
    val password:String,
    val phoneNumber:String,
    val roles:String,
    @Enumerated(EnumType.STRING)
    val type:UserType,
    @OneToOne(mappedBy = "owner")
    @JsonBackReference
    val localeManages:Locale?,
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    val reservation:List<Reservation>?
)
