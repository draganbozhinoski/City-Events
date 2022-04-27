package com.sorsix.cityevents.api

import com.sorsix.cityevents.api.requests.AuthRequest
import com.sorsix.cityevents.api.requests.UserRequest
import com.sorsix.cityevents.api.responses.UserError
import com.sorsix.cityevents.api.responses.UserResponse
import com.sorsix.cityevents.api.responses.UserSuccess
import com.sorsix.cityevents.domain.User
import com.sorsix.cityevents.domain.view.UserJwt
import com.sorsix.cityevents.jwt.JwtUtils
import com.sorsix.cityevents.repository.UsersRepository
import com.sorsix.cityevents.service.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UsersRepository,
    val encoder: PasswordEncoder,
    val jwtUtils: JwtUtils,
    val myUsersService: UserDetailsServiceImpl
) {
    @PostMapping("/register")
    fun register(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return when (userRepository.findByUsername(userRequest.username).isPresent) {
            true -> {
                ResponseEntity.badRequest().body(UserError("User already in the database"))
            }
            else -> {
                ResponseEntity.ok().body(
                    UserSuccess(
                        userRepository.save(
                            User(
                                username = userRequest.username,
                                name = userRequest.name,
                                email = userRequest.email,
                                password = encoder.encode(userRequest.password),
                                phoneNumber = userRequest.phoneNumber,
                                localeManages = null,
                                reservation = null,
                                type = userRequest.role
                            )
                        )
                    )
                )
            }
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<UserJwt> {
        val authentication = this.authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        )
        val userDetails: UserDetails = this.myUsersService.loadUserByUsername(authRequest.username)
        val user: User = userRepository.findByUsername(userDetails.username).get()
        val jwt: String = this.jwtUtils.generateJwtToken(authentication)
        return ResponseEntity.ok().body(UserJwt(user.username, user.name, user.email, user.phoneNumber, user.type, jwt))
    }
}