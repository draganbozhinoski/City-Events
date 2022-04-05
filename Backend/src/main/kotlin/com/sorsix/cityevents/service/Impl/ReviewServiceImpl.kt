package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.repository.LocaleRepository
import com.sorsix.cityevents.repository.ReviewsRepository
import com.sorsix.cityevents.service.ReviewService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(val reviewsRepository: ReviewsRepository,val localeRepository:LocaleRepository):ReviewService {
    override fun addReview(review: String, stars: Int,locale:Locale) {
        reviewsRepository.save(Review(review=review,stars=stars, locale = locale))
    }
}