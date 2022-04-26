package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.service.ReviewService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reviews")
class ReviewController(val reviewService: ReviewService) {
    val logger:Logger = LoggerFactory.getLogger("Reviews controller")
    //returns all reviews in the database
    @GetMapping
    fun findAllReviews() = reviewService.findAll()

    //deletes review by given id
    @DeleteMapping("/delete/{id}")
    fun deleteReview(@PathVariable id:Long): ResponseEntity<List<Review>> {
        return when(val review: ReviewResponse = reviewService.deleteReview(id)) {
            is ReviewSuccess -> {
                logger.info("Review deleted successfully.")
                ResponseEntity.ok().body(reviewService.findAll())
            }
            is ReviewError -> {
                logger.error("Problem with deleting review, it was not found in the database.")
                ResponseEntity.badRequest().body(reviewService.findAll())
            }
        }
    }
}