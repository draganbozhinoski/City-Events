package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.ReviewResponse
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.Review

interface ReviewService {
    fun addReview(review:String, stars:Int,locale: Locale)
    fun deleteReview(id:Long):ReviewResponse
    fun findAll():List<Review>
    fun deleteAll(reviewsList:List<Review>)
}