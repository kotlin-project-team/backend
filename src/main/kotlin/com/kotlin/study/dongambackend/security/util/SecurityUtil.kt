package com.kotlin.study.dongambackend.security.util

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest


@PropertySource("classpath:application-jwt.yml")
@Component
class SecurityUtil(
    @Value("\${secret-key}") private var secretKey: String,
    private val tokenProvider: TokenProvider
) {

    private val TOKEN_HEADER_NAME = "Authorization"
    private val TOKEN_HEADER_PREFIX = "Bearer "

    @PostConstruct
    protected fun init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun getBearerToken(request: HttpServletRequest): String? {
        val token = request.getHeader(TOKEN_HEADER_NAME)

        return token?.takeIf { it.startsWith(TOKEN_HEADER_PREFIX) }?.substring(7)
    }

    fun getUserFromToken(token: String) = token.let { this.getSubjectFromToken(it) }

    private fun getSubjectFromToken(token: String) =
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
}