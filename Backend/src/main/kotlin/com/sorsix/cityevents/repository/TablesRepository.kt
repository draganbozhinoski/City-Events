package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.domain.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TablesRepository:JpaRepository<Table,Long> {
    @Modifying
    @Query("update Table ta set ta.reserved=:reserved where ta.id=:id")
    fun updateTable(id:Long, reserved:Boolean)
}