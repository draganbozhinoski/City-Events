package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.api.responses.EventSuccess
import com.sorsix.cityevents.api.responses.LocaleError
import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.LocaleSuccess
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import com.sorsix.cityevents.repository.LocaleRepository
import com.sorsix.cityevents.service.LocaleService
import com.sorsix.cityevents.service.ReviewService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class LocaleServiceImpl(val localeRepository: LocaleRepository,val reviewService:ReviewService) :LocaleService{
    override fun getLocale(id :Long): LocaleResponse {
        return when(val  result = localeRepository.findByIdOrNull(id)){
            null->LocaleError("Can't find locale with that id!")
            else->LocaleSuccess(result)
        }
    }

    override fun getAll(): List<Locale> {
        return localeRepository.findAll()
    }

    override fun saveLocale(name:String,
                            type: LocaleType): LocaleResponse {
        if(type in LocaleType.values()){
            return LocaleSuccess(localeRepository.save(Locale(name=name,type=type)))
        }
        return LocaleError("Type missmatch")

    }
    @Transactional
    override fun updateLocale(id:Long, name: String, type: LocaleType): LocaleResponse {
        localeRepository.update(id,name,type)
        return LocaleSuccess(localeRepository.findByIdOrNull(id)!!)
    }
    @Transactional
    override fun deleteById(id: Long): LocaleResponse {
        return when(val locale = localeRepository.findByIdOrNull(id)) {
            null -> LocaleError("Locale with that id was not found in the database.")
            else -> {
                localeRepository.delete(locale)
                LocaleSuccess(locale)
            }
        }
    }
    //cistenje na lokal, potrebno za brishenje na istiot
    override fun clearLocale(id: Long): LocaleResponse {
        return when(val locale = localeRepository.findByIdOrNull(id)) {
            null -> LocaleError("Locale with that id was not found in the database.")
            else -> {
                locale.eventsList.clear()
                reviewService.deleteAll(locale.reviewsList)
                locale.reviewsList.clear()
                locale.tablesList.clear()
                locale.reservationsList.clear()
                LocaleSuccess(locale)
            }
        }
    }
}