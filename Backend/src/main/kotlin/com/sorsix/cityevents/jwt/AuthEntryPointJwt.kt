package com.sorsix.cityevents.jwt

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPointJwt:AuthenticationEntryPoint {
    val logger:Logger = LoggerFactory.getLogger("AuthEntryPointJwt")

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        logger.error("Unauthorized error: {}",authException?.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error:Unauthorized")
    }

}