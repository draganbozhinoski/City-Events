package com.sorsix.cityevents.api.responses

import com.sorsix.cityevents.domain.Event

sealed interface EventResponse

class EventSuccess(val event:Event) : EventResponse
class EventError(val errorMessage:String) : EventResponse