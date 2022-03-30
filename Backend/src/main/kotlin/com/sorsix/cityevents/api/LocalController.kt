package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.api.requests.LocaleDto
import com.sorsix.cityevents.api.responses.LocaleError
import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.LocaleSuccess
import com.sorsix.cityevents.service.LocaleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locale")
class LocalController (val localeService: LocaleService){

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
    fun saveLocale(@RequestBody locale: LocaleDto):Locale {
        with(locale) {
            return localeService.saveLocale(id, name, type, tablesList, reservationList, eventsList, reviewsList)
        }
    }

}