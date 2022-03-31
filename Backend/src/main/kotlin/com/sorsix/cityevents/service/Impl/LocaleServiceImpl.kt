package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.api.responses.LocaleError
import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.LocaleSuccess
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import com.sorsix.cityevents.repository.LocaleRepository
import com.sorsix.cityevents.repository.TablesRepository
import com.sorsix.cityevents.service.LocaleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LocaleServiceImpl(val repository: LocaleRepository, val tablesRepository: TablesRepository) : LocaleService {
    override fun getLocale(id: Long): LocaleResponse {
        return when (val result = repository.findByIdOrNull(id)) {
            null -> LocaleError("Can't find locale with that id!")
            else -> LocaleSuccess(result)
        }
    }

    override fun getAll(): List<Locale> {
        return repository.findAll()
    }

    override fun saveLocale(
        name: String,
        type: LocaleType,
        numTables: Int
    ): Locale {
        val locale = Locale(name = name, type = type, numTables = numTables)
        return repository.save(locale)
    }

    override fun initTables(locale: Locale):Locale {
        for(i in 0 until locale.numTables) {
            locale.tablesList.add(tablesRepository.save(Table(reserved=false, reservation = null,locale=locale)))
        }
        return locale
    }
}