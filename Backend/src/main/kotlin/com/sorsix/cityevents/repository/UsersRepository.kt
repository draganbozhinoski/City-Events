package com.sorsix.cityevents.repository

import com.sorsix.cityevents.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsersRepository : JpaRepository<User,Long> {
    fun findByUsername(username:String): Optional<User>
}