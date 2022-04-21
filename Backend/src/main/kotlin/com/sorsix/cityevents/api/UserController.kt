package com.sorsix.cityevents.api

import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.view.UserFront
import com.sorsix.cityevents.service.MyUsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(val usersService:MyUsersService) {
    @GetMapping
    fun findAllUsers():ResponseEntity<List<UserFront>> {
        return ResponseEntity.ok().body(usersService.findAllUsers().map { user -> UserFront(user.id,user.username,user.name,user.email,user.phoneNumber,user.type) })
    }
}