package com.kotlin.study.dongambackend.domain.user.dto.response

data class SignInResponse(
    val id: Long,
    val studentId: String,
    val nickname: String,
    val accessToken: String,
    val refreshToken: String
)