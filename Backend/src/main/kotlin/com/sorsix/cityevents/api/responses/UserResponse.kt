package com.sorsix.cityevents.api.responses

import com.sorsix.cityevents.domain.User

sealed interface UserResponse

data class UserSuccess(val user: User):UserResponse
data class UserError(val errorMessage:String):UserResponse