package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.AuthRequest
import com.sorsix.cityevents.api.requests.UserRequest
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.enums.UserType
import com.sorsix.cityevents.domain.view.UserJwt
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
    fun register(@RequestBody userRequest: UserRequest):ResponseEntity<Any> {
        return when(userRepository.findByUsername(userRequest.username).isPresent) {
            true -> {
                ResponseEntity.badRequest().body("User already in the database")
            }
            else -> {
                userRepository.save(User(username = userRequest.username,name=userRequest.name,email=userRequest.email, password = encoder.encode(userRequest.password), phoneNumber = userRequest.phoneNumber, localeManages = null, reservation = null, type = UserType.ROLE_GUEST))
                ResponseEntity.ok().body("User saved")
            }
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest:AuthRequest):ResponseEntity<UserJwt> {
        try{
            this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.username,authRequest.password)
            )
        }catch (e: BadCredentialsException){
            throw Exception("Incorrect username or password",e)
        }
        val userDetails: UserDetails = this.myUsersService.loadUserByUsername(authRequest.username)
        val user:User = userRepository.findByUsername(userDetails.username).get()
        val jwt:String = this.jwtUtils.generateToken(userDetails)
        return ResponseEntity.ok().body(UserJwt(user,jwt))
    }
}