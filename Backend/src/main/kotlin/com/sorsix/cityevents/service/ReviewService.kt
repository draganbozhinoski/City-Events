package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.ReviewError
import com.sorsix.cityevents.api.responses.ReviewResponse
import com.sorsix.cityevents.api.responses.ReviewSuccess
import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.repository.LocalesRepository
import com.sorsix.cityevents.repository.ReviewsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ReviewService(val reviewsRepository: ReviewsRepository, val localesRepository:LocalesRepository) {
    fun addReview(review: String, stars: Int,locale:Locale) {
        reviewsRepository.save(Review(review=review,stars=stars, locale = locale))
    }
    @Transactional
    fun deleteReview(id: Long):ReviewResponse {
        return when(val review = reviewsRepository.findByIdOrNull(id)) {
            null -> ReviewError("We can't find that review in the database.")
            else -> {
                reviewsRepository.delete(review)
                ReviewSuccess(review)
            }
        }
    }

    fun findAll():List<Review> {
        return reviewsRepository.findAll()
    }

    fun deleteAll(reviewsList:List<Review>) {
        reviewsRepository.deleteAll(reviewsList)
    }
}