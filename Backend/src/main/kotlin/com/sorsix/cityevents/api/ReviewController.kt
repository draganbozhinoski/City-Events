package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.ReviewRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.service.ReviewService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reviews")
class ReviewController(val reviewService:ReviewService) {
    val logger:Logger = LoggerFactory.getLogger("Reviews controller")
    //returns all reviews in the database
    @GetMapping
    fun findAllReviews() = reviewService.findAll()

    //deletes review by given id
    @DeleteMapping("/{id}/delete")
    fun deleteReview(@PathVariable id:Long): ResponseEntity<ReviewResponse> {
        return when(val review: ReviewResponse = reviewService.deleteReview(id)) {
            is ReviewSuccess -> {
                logger.info("Review deleted successfully.")
                ResponseEntity.ok().body(review)
            }
            is ReviewError -> {
                logger.error("Problem with deleting review, it was not found in the database.")
                ResponseEntity.badRequest().body(review)
            }
        }
    }
}