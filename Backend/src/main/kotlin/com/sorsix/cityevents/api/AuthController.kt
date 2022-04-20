package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.AuthRequest
import com.sorsix.cityevents.api.requests.UserRequest
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.enums.UserType
import com.sorsix.cityevents.jwt.JwtUtil
import com.sorsix.cityevents.repository.UsersRepository
import com.sorsix.cityevents.service.MyUsersService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UsersRepository,
    val encoder: PasswordEncoder,
    val jwtUtils: JwtUtil,
    val myUsersService: MyUsersService
) {
    @PostMapping("/register")
    fun register(@RequestBody userRequest: UserRequest) {
        userRepository.save(User(username = "test",name="test",email="test", password = encoder.encode("test"), phoneNumber = "071",roles="ROLE_ADMIN", localeManages = null, reservation = null, type = UserType.ROLE_ADMIN))
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest:AuthRequest):ResponseEntity<String> {
        try{
            this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.username,authRequest.password)
            )
        }catch (e: BadCredentialsException){
            throw Exception("Incorrect username or password",e)
        }
        val userDetails: UserDetails = this.myUsersService.loadUserByUsername(authRequest.username)
        val jwt:String = this.jwtUtils.generateToken(userDetails)
        return ResponseEntity.ok().body(jwt)

    }
}