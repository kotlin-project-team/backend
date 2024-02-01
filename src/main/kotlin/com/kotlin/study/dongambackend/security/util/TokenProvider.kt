package com.kotlin.study.dongambackend.security.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParserBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

import java.sql.Timestamp
import java.time.Duration

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

import java.util.*
import javax.annotation.PostConstruct

import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpServletRequest

@PropertySource("classpath:application-jwt.yml")
@Component
class TokenProvider(
    @Value("\${secret-key}") private var secretKey: String,
    @Value("\${access-token-time}") private val accessTokenTime: Long,
    @Value("\${refresh-token-time}") private val refreshTokenTime: Long,
    private val redisTemplate: RedisTemplate<String, String>
) {

    private val TOKEN_HEADER_NAME = "Authorization"
    private val TOKEN_HEADER_PREFIX = "Bearer "

    fun createAccessToken(role: String, userId: Long): String {
        return this.createToken(role, userId, accessTokenTime)
    }

    fun createRefreshToken(role: String, userId: Long): String {
        val refreshToken = this.createToken(role, userId, refreshTokenTime)
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.set(userId.toString(), refreshToken, Duration.ofMillis(refreshTokenTime))

        return refreshToken
    }

    private fun createToken(role: String, userId: Long, durationTime: Long): String {
        val claims = Jwts.claims().apply {
            this["userId"] = userId
            this["Role"] = role
        }

        val signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date.from(Instant.now().plusMillis(durationTime)))
            .compact()
    }

    fun getBearerToken(request: HttpServletRequest): String? {
        val token = request.getHeader(TOKEN_HEADER_NAME)

        return token?.takeIf { it.startsWith(TOKEN_HEADER_PREFIX) }?.substring(7)
    }

    fun getUserIdFromToken(token: String) = token.let { this.getSubjectFromToken(it)["userId"] }

    private fun getSubjectFromToken(token: String): Claims {
        val signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}