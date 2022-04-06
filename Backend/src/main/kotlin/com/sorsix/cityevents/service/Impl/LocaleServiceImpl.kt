package com.sorsix.cityevents.service.Impl

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import com.sorsix.cityevents.repository.*
import com.sorsix.cityevents.service.LocaleService
import com.sorsix.cityevents.service.ReviewService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class LocaleServiceImpl(val localeRepository: LocaleRepository,
                        val reviewsRepository:ReviewsRepository,
                        val eventsRepository: EventsRepository,
                        val tablesRepository:TablesRepository,
                        val reservationsRepository:ReservationsRepository) :LocaleService{
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
                            type: LocaleType,
                            numTables:Int): LocaleResponse {
        if(type in LocaleType.values()){
            val locale = localeRepository.save(Locale(name=name,type=type))
            for(i in 0 until numTables) {
                tablesRepository.save(Table(reserved=false, reservation = null,locale=locale))
            }
            return LocaleSuccess(locale)
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
                reviewsRepository.deleteAll(locale.reviewsList)
                eventsRepository.deleteAll(locale.eventsList)
                tablesRepository.deleteAll(locale.tablesList)
                reservationsRepository.deleteAll(locale.reservationsList)
                LocaleSuccess(locale)
            }
        }
    }

    override fun reserveTable(id: Long,name:String,phoneNumber:String,dateTime:LocalDateTime,username:String,description:String): ReservationResponse {
        return when(val locale = getLocale(id)) {
            is LocaleSuccess -> {
                for(table in locale.locale.tablesList) {
                    if(!table.reserved) {
                        val reservation = reservationsRepository.save(Reservation(name=name,description=description,phoneNumber=phoneNumber,dateTime=dateTime,table=table,locale=locale.locale,user=null))
                        tablesRepository.updateTable(table.id,reservation,true)
                        ReservationSuccess(reservation)
                    }
                }
                ReservationError("All tables are reserved.")
            }
            is LocaleError -> {
                ReservationError("Locale with that id was not found")
            }
        }
    }
}