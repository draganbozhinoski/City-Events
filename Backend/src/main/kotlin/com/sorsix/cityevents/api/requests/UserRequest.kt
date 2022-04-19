package com.sorsix.cityevents.api.requests

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.enums.UserType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

data class UserRequest(
    val name:String,
    val username:String,
    val password:String,
    val email:String,
    val phoneNumber:String,
    val type: UserType,
    val localeId: Long
    )
