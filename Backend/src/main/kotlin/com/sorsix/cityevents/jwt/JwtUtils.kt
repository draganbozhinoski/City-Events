package com.sorsix.cityevents.jwt

import com.sorsix.cityevents.domain.UserDetailsImpl
import com.sorsix.cityevents.service.UserDetailsServiceImpl
import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.util.*

@Component
class JwtUtils {
    val logger:Logger = LoggerFactory.getLogger("JWTUtils")
    val jwtSecret:String = "taenKod"
    val jwtExpirationMs:Int = 90000


    fun generateJwtToken(authentication:Authentication):String {
        val userPrincipal = authentication.principal as UserDetailsImpl
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512,jwtSecret)
            .compact()
    }
    fun getUsernameFromJwtToken(token:String):String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).body.subject
    }
    fun validateJwtToken(authToken:String):Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        }
        catch(e:SignatureException) {
            logger.error("Invalid JWT signature")
        }
        catch (e:MalformedJwtException) {
            logger.error("Invalid JWT Token")
        }
        catch(e:ExpiredJwtException) {
            logger.error("JWT Token is expired")
        }
        catch(e:UnsupportedJwtException) {
            logger.error("JWT token is unsupported")
        }
        catch(e:IllegalArgumentException) {
            logger.error("Illegal argument")
        }
        return false
    }
}