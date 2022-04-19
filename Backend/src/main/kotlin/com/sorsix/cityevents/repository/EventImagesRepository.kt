package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.view.EventImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventImagesRepository:JpaRepository<EventImage,Long> {
}