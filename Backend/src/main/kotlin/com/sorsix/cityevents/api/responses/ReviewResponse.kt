package com.sorsix.cityevents.api.responses

import com.sorsix.cityevents.domain.Review

sealed interface ReviewResponse

data class ReviewSuccess(val review:Review): ReviewResponse
data class ReviewError(val errorMessage:String): ReviewResponse