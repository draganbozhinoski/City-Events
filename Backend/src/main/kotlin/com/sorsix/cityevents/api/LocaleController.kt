package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.Locale
import com.sorsix.cityevents.api.requests.LocaleRequest
import com.sorsix.cityevents.api.requests.ReviewRequest
import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.Review
import com.sorsix.cityevents.service.LocaleService
import com.sorsix.cityevents.service.ReviewService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.transaction.TransactionScoped
import javax.transaction.Transactional

@RestController
@RequestMapping("/api/locales")
class LocaleController (val localeService: LocaleService,val reviewService:ReviewService){
    val logger:Logger = LoggerFactory.getLogger("Locale controller")
    //getbyid
    //read
    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long):ResponseEntity<LocaleResponse>{
        return when(val locale = localeService.getLocale(id)){
            is LocaleSuccess -> ResponseEntity.ok().body(locale)
            is LocaleError -> ResponseEntity.badRequest().body(locale)
        }
    }
    //findall
    @GetMapping
    fun getLocales():List<Locale>{
        return localeService.getAll()
    }
    //create
    @PostMapping("/save")
    fun saveLocale(@RequestBody localeRequest: LocaleRequest):ResponseEntity<LocaleResponse> {
        with(localeRequest) {
            return when(val locale = localeService.saveLocale(name=name,type=type)) {
                is LocaleError -> {
                    logger.error(locale.errorMessage)
                    ResponseEntity.badRequest().body(locale)
                }
                is LocaleSuccess -> {
                    logger.info("Locale was saved successfully.")
                    ResponseEntity.ok().body(locale)
                }
            }
        }
    }
    //update
    @PutMapping("/update/{id}")
    fun updateLocale(@RequestBody localeRequest:LocaleRequest,@PathVariable id:Long):ResponseEntity<LocaleResponse> {
        with(localeRequest) {
            return when(val locale = localeService.getLocale(id)) {
                is LocaleSuccess -> {
                    localeService.updateLocale(id,name,type)
                    logger.info("Locale updated successfully")
                    ResponseEntity.ok().body(localeService.getLocale(id))
                }
                is LocaleError -> {
                    logger.error("Some error occured")
                    ResponseEntity.badRequest().body(locale)
                }
            }
        }
    }
    //delete
    //TODO: Brishenje na sve entitetite pred brishenje na lokalot, da se naprae u when findById pa funkcie clear locale.
    @DeleteMapping("/delete/{id}")
    fun deleteLocale(@PathVariable id:Long):ResponseEntity<String> {
        return when(localeService.deleteById(id)) {
            is LocaleSuccess -> {
                logger.info("Locale deleted successfully")
                ResponseEntity.ok().body("locale with id $id was successfully deleted.")
            }
            is LocaleError -> {
                logger.error("locale with that id was not found or something else occured")
                ResponseEntity.badRequest().body("Locale with that id was not found")
            }
        }
    }
    //expects locale id
    //returns all the reviews in the given locale
    @GetMapping("/{id}/reviews")
    fun getAllReviewsByLocaleId(@PathVariable id:Long):ResponseEntity<List<Review>> {
        return when(val locale = localeService.getLocale(id)) {
            is LocaleSuccess -> {
                logger.info("Locale was found by the id, attempting to get reviews..")
                val reviews = locale.locale.reviewsList
                ResponseEntity.ok().body(reviews)
            }
            is LocaleError -> {
                logger.error("Locale with that id was not found.")
                ResponseEntity.badRequest().body(listOf())
            }
        }
    }
    //expects Locale id, review
    //creates and adds review to the given locale
    @PostMapping("/{id}/reviews/add")
    fun addReview(@PathVariable id:Long, @RequestBody reviewRequest: ReviewRequest): ResponseEntity<List<Review>> {
        with(reviewRequest) {
            return when (val locale = localeService.getLocale(id)) {
                is LocaleSuccess -> {
                    logger.info("Locale was found, attempting to add review")
                    locale.locale.reviewsList.add(Review(review=review,stars=stars,locale=locale.locale))
                    reviewService.addReview(review,stars,locale.locale)
                    ResponseEntity.ok().body(locale.locale.reviewsList)
                }
                is LocaleError -> {
                    logger.error("Locale cannot be found by that id")
                    ResponseEntity.badRequest().body(listOf())
                }
            }
        }
    }
}