package com.kotlin.study.dongambackend.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

import java.sql.Timestamp

import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import java.util.*

import javax.crypto.spec.SecretKeySpec

@PropertySource("classpath:application-jwt.yml")
@Component
class TokenProvider(
    @Value("\${secret-key}") private val secretKey: String,
    @Value("\${access-token-time}") private val accessTokenTime: Long,
    @Value("\${refresh-token-time}") private val refreshTokenTime: Long
) {
    private val ACCESS_TOKEN_HEADER = "bearer"

    fun createAccessToken(role: String, studentId: String): String {
        val claims = Jwts.claims().setSubject(studentId)
        claims.put("Role", role)

        return Jwts.builder()
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS256.jcaName))
            .setClaims(claims)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(accessTokenTime, ChronoUnit.HOURS)))
            .compact()
    }
}