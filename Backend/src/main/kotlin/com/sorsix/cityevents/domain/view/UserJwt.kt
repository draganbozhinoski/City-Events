package com.sorsix.cityevents.domain.view

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.enums.UserType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import javax.persistence.OneToOne

data class UserJwt(val username: String,
                   val name:String,
                   val email:String,
                   val phoneNumber:String,
                   val type:UserType,
                   val jwt:String)