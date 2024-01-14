package com.kotlin.study.dongambackend.security.util

import com.kotlin.study.dongambackend.security.exception.TokenNotFountException
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class SecurityUtil {

    private val TOKEN_HEADER_NAME = "Authorization"
    private val TOKEN_HEADER_PREFIX = "bearer "

    fun getBearerToken(request: HttpServletRequest): String {
        val token = request.getHeader(TOKEN_HEADER_NAME)
            ?: throw TokenNotFountException()

        return token.takeIf { it.startsWith(TOKEN_HEADER_PREFIX) }?.substring(7)
            ?: throw TokenNotFountException()
    }
}