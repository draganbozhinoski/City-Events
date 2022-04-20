package com.sorsix.cityevents.service

import com.sorsix.cityevents.domain.MyUserDetails
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyUsersService(val userRepository:UsersRepository):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return MyUserDetails(userRepository.findByUsername(username).map { it }.orElseThrow{
            UsernameNotFoundException("User not found")
        })
    }
}