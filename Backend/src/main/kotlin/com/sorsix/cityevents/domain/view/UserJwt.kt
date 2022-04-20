package com.sorsix.cityevents.domain.view

import com.sorsix.cityevents.domain.User

data class UserJwt(val user: User, val jwt:String)