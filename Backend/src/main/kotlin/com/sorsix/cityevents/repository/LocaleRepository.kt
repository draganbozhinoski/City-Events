package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Locale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocaleRepository : JpaRepository<Locale,Long>