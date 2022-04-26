package com.sorsix.cityevents.domain.view

import com.sorsix.cityevents.domain.enums.UserType

data class UserFront (
    val id:Long,
    val username:String,
    val name:String,
    val email:String,
    val phoneNumber:String,
    val type:UserType
)