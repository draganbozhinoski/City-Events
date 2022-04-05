package com.sorsix.cityevents.service

import com.sorsix.cityevents.domain.Locale

interface ReviewService {
    fun addReview(review:String, stars:Int,locale: Locale)
}