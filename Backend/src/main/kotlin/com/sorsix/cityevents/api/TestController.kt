package com.sorsix.cityevents.api

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_GUEST') or hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
    fun getAll():String {
        return "All"
    }
    @GetMapping("/owner")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    fun getOwner():String {
        return "Owner"
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAdmin():String {
        return "Admin"
    }
}