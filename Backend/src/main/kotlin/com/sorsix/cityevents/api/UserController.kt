package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.service.MyUsersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(val usersService:MyUsersService) {
    @GetMapping
    fun findAllUsers():List<User> {
        return usersService.findAll()
    }
}