package com.sorsix.cityevents.service

import com.sorsix.cityevents.api.responses.*
import com.sorsix.cityevents.domain.UserDetailsImpl
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.repository.UsersRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository:UsersRepository):UserDetailsService {
    fun findAll():List<User> {
        return userRepository.findAll()
    }
    override fun loadUserByUsername(username: String): UserDetails {
        return UserDetailsImpl(userRepository.findByUsername(username).map { it }.orElseThrow{
            UsernameNotFoundException("User not found")
        })
    }
    fun findAllUsers():List<User> {
        return userRepository.findAll()
    }
    fun findById(id:Long): UserResponse {
        return when (val result = userRepository.findByIdOrNull(id)) {
            null -> UserError("Can't find user with that id!")
            else -> UserSuccess(result)
        }
    }

    fun findByUsername(username: String): User {
        return userRepository.findByUsername(username).get()
    }

    fun deleteById(id:Long) {
        userRepository.deleteById(id)
    }
}