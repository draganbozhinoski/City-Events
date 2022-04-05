package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.domain.enums.LocaleType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LocaleRepository : JpaRepository<Locale,Long> {
    @Modifying
    @Query("update Locale l set l.name=:name,l.type=:type where l.id=:id")
    fun update(id:Long,name:String,type:LocaleType)
}