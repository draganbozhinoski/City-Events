package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TablesRepository:JpaRepository<Table,Long>