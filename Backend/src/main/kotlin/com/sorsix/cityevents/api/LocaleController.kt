package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.api.requests.LocaleRequest
import com.sorsix.cityevents.api.responses.LocaleError
import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.LocaleSuccess
import com.sorsix.cityevents.service.LocaleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locale")
class LocaleController (val localeService: LocaleService){
    val logger:Logger = LoggerFactory.getLogger("Locale logger")

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long):ResponseEntity<LocaleResponse>{
        return when(val locale = localeService.getLocale(id)){
            is LocaleSuccess -> ResponseEntity.ok().body(locale)
            is LocaleError -> ResponseEntity.badRequest().body(locale)
        }
    }

    @GetMapping
    fun getLocales():List<Locale>{
        return localeService.getAll()
    }

    @PostMapping("/save")
    fun saveLocale(@RequestBody locale: LocaleRequest):Locale {
        with(locale) {
            logger.info("Locale was saved in the database")
            return localeService.initTables(localeService.saveLocale(name = name,type = type, numTables = numTables))
        }
    }


}