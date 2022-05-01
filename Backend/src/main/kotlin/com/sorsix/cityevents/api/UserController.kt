package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.responses.UserError
import com.sorsix.cityevents.api.responses.UserSuccess
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.view.UserFront
import com.sorsix.cityevents.service.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(val usersService:UserDetailsServiceImpl) {
    @GetMapping
    fun findAllUsers():List<UserFront> {
        return usersService.findAllUsers().map { user -> UserFront(user.id,user.username,user.name,user.email,user.phoneNumber,user.type) }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long):ResponseEntity<Any>{
        return when (val user = usersService.findById(id)) {
            is UserSuccess -> ResponseEntity.ok().body(user.user)
            is UserError -> ResponseEntity.badRequest().body(user)
        }
    }

    @GetMapping("/username/{username}")
    fun findById(@PathVariable username:String):ResponseEntity<Any>{
        return ResponseEntity.ok().body(usersService.findByUsername(username))
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id:Long):ResponseEntity<List<UserFront>> {
        usersService.deleteById(id)
    return ResponseEntity.ok().body(usersService.findAll().map { it -> UserFront(it.id,it.username,it.name,it.email,it.phoneNumber,it.type) })
    }
}