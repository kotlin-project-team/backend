package com.kotlin.study.dongambackend.security.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

import java.sql.Timestamp
import java.time.Duration

import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import java.util.*
import javax.annotation.PostConstruct

import javax.crypto.spec.SecretKeySpec

@PropertySource("classpath:application-jwt.yml")
@Component
class TokenProvider(
    @Value("\${secret-key}") private var secretKey: String,
    @Value("\${access-token-time}") private val accessTokenTime: Long,
    @Value("\${refresh-token-time}") private val refreshTokenTime: Long,
    private val redisTemplate: RedisTemplate<String, String>
) {

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
        val claims = Jwts.claims()
        claims["userId"] = userId
        claims["Role"] = role

        return Jwts.builder()
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS256.jcaName))
            .setClaims(claims)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(durationTime, ChronoUnit.HOURS)))
            .compact()
    }
}