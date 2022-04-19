package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.*
import com.sorsix.cityevents.domain.enums.LocaleType
import com.sorsix.cityevents.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class LocaleService(
    val localesRepository: LocalesRepository,
    val reviewsRepository: ReviewsRepository,
    val eventsRepository: EventsRepository,
    val tablesRepository: TablesRepository,
    val reservationsRepository: ReservationsRepository
) {
    fun getLocale(id: Long): LocaleResponse {
        return when (val result = localesRepository.findByIdOrNull(id)) {
            null -> LocaleError("Can't find locale with that id!")
            else -> LocaleSuccess(result)
        }
    }

    fun getAll(): List<Locale> {
        return localesRepository.findAll()
    }

    fun saveLocale(
        name: String,
        type: LocaleType,
        numTables: Int,
        logoUrl: String
    ): LocaleResponse {
        if (type in LocaleType.values()) {
            val locale = localesRepository.save(Locale(name = name, type = type, numTables = numTables, logoUrl = logoUrl))
            for (i in 0 until numTables) {
                tablesRepository.save(Table(reserved = false, reservation = null, locale = locale))
            }
            return LocaleSuccess(locale)
        }
        return LocaleError("Type missmatch")

    }

    @Transactional
    fun updateLocale(id: Long, name: String, type: LocaleType,logoUrl: String): LocaleResponse {
        localesRepository.update(id, name, type,logoUrl)
        return LocaleSuccess(localesRepository.findByIdOrNull(id)!!)
    }

    @Transactional
    fun deleteById(id: Long): LocaleResponse {
        return when (val locale = localesRepository.findByIdOrNull(id)) {
            null -> LocaleError("Locale with that id was not found in the database.")
            else -> {
                localesRepository.delete(locale)
                LocaleSuccess(locale)
            }
        }
    }

    //cistenje na lokal, potrebno za brishenje na istiot
    fun clearLocale(id: Long): LocaleResponse {
        return when (val locale = localesRepository.findByIdOrNull(id)) {
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
    //finds first not reserved table and makes it reserved.
    // returns reserved table, if there are none, returns reservationerror.
    @Transactional
    fun reserveTable(
        id: Long,
        name: String,
        phoneNumber: String,
        dateTime: LocalDateTime,
        username: String,
        description: String
    ): ReservationResponse {
        return when (val locale = getLocale(id)) {
            is LocaleSuccess -> {
                when(val table = locale.locale.tablesList.find { t -> !t.reserved }) {
                    null -> ReservationError("All tables are reserved.")
                    else -> {
                        val reservation:Reservation = reservationsRepository.save(
                            Reservation(
                                name = name,
                                description = description,
                                phoneNumber = phoneNumber,
                                dateTime = dateTime,
                                table = table,
                                locale = locale.locale,
                                user = null
                            )
                        )
                        tablesRepository.updateTable(table.id,true)
                        ReservationSuccess(reservation)
                    }
                }
            }
            is LocaleError -> {
                ReservationError("Locale with that id was not found")
            }
        }
    }

    fun getRating(id: Long): Double {
        return when(val locale=getLocale(id)) {
            is LocaleSuccess -> {
                locale.locale.reviewsList.map { m -> m.stars }.average()
            }
            is LocaleError -> {
                (-1).toDouble()
            }
        }
    }
}