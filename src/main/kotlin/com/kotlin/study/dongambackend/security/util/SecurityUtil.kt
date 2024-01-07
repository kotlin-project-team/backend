package com.kotlin.study.dongambackend.security.util

import com.kotlin.study.dongambackend.security.exception.TokenNotFountException
import javax.servlet.http.HttpServletRequest

class SecurityUtil() {

    private val TOKEN_HEADER_NAME = "Authorization"
    private val TOKEN_HEADER_PREFIX = "bearer "

    fun checkBearerToken(request: HttpServletRequest): String {
        val token = request.getHeader(TOKEN_HEADER_NAME)
            ?: throw TokenNotFountException()

        return token.takeIf { it.startsWith(TOKEN_HEADER_PREFIX) }?.substring(7)
            ?: throw TokenNotFountException()
    }
}