package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import org.springframework.stereotype.Service

@Service
interface LocaleService {
    fun getLocale(id :Long):LocaleResponse
    fun getAll():List<Locale>
    fun saveLocale(id: Long,
                   name:String,
                   type: LocaleType,
                   tablesList:List<Table>,
                   reservationList:List<Reservation>,
                   eventsList:List<Event>, reviewsList:List<Review>):Locale
}