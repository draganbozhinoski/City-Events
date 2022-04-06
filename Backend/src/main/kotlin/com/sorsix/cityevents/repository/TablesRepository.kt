package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Reservation
import com.sorsix.cityevents.domain.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TablesRepository:JpaRepository<Table,Long> {
    @Query("update Table t set t.reservation=:reservation,t.reserved=:reserved where t.id=:id")
    fun updateTable(id:Long,reservation:Reservation,reserved:Boolean)
}