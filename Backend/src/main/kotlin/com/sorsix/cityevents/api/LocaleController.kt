package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.api.requests.LocaleRequest
import com.sorsix.cityevents.api.responses.LocaleError
import com.sorsix.cityevents.api.responses.LocaleResponse
import com.sorsix.cityevents.api.responses.LocaleSuccess
import com.sorsix.cityevents.service.LocaleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("/api/locale")
class LocaleController (val localeService: LocaleService){

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
            return localeService.saveLocale(name = name,type = type)
        }
    }

}