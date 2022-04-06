package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.ReservationResponse
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
interface LocaleService {
    fun getLocale(id :Long):LocaleResponse
    fun getAll():List<Locale>
    fun saveLocale(name:String,
                   type: LocaleType,numTables:Int):LocaleResponse
    fun updateLocale(id:Long, name:String, type:LocaleType):LocaleResponse
    fun deleteById(id:Long):LocaleResponse
    fun clearLocale(id:Long):LocaleResponse
    fun reserveTable(id: Long, name:String, phoneNumber:String, dateTime: LocalDateTime, username:String,description:String):ReservationResponse
}