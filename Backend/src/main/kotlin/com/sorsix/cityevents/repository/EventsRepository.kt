package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventsRepository: JpaRepository<Event, Long> {
}