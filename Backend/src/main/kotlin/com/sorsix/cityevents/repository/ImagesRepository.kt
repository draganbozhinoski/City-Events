package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImagesRepository:JpaRepository<Image,Long> {
}