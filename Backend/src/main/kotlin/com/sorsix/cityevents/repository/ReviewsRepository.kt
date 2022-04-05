package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewsRepository: JpaRepository<Review,Long>