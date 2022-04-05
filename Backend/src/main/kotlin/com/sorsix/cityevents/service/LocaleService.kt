package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import org.springframework.stereotype.Service

@Service
interface LocaleService {
    fun getLocale(id :Long):LocaleResponse
    fun getAll():List<Locale>
    fun saveLocale(name:String,
                   type: LocaleType):LocaleResponse
}