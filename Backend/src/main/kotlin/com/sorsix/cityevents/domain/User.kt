package com.sorsix.cityevents.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sorsix.cityevents.domain.enums.UserType
import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "city_users")
data class User(
    @Id
    val username:String,
    val name:String,
    val email:String,
    val password:String,
    val phoneNumber:String,
    @Enumerated(EnumType.STRING)
    val type:UserType,
    @OneToOne
    val localeManages:Locale?,
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    val reservation:List<Reservation>?
)
