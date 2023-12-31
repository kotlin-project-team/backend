package com.kotlin.study.dongambackend.domain.user.dto.request

data class SignInRequest(
    val studentId: String,
    val password: String
)