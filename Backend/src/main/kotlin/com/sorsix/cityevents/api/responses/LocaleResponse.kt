package com.sorsix.cityevents.api.responses

import com.sorsix.cityevents.domain.Locale

sealed interface LocaleResponse

class LocaleSuccess(val locale: Locale):LocaleResponse
class LocaleError(val errorMessage:String):LocaleResponse