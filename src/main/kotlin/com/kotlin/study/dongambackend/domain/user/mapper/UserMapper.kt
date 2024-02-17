package com.kotlin.study.dongambackend.domain.user.mapper

import com.kotlin.study.dongambackend.domain.user.dto.request.CreateUserRequest
import com.kotlin.study.dongambackend.domain.user.entity.User
import com.kotlin.study.dongambackend.domain.user.validator.type.UserRole

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toUser(createRequest: CreateUserRequest, passwordEncoder: PasswordEncoder) =
        User(
            createRequest.studentId,
            passwordEncoder.encode(createRequest.password),
            createRequest.nickname,
            UserRole.ROLE_USER,
            createRequest.deviceToken
        )
}