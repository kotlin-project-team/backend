package com.kotlin.study.dongambackend.domain.user.mapper

import com.kotlin.study.dongambackend.domain.user.dto.request.UserCreateRequest
import com.kotlin.study.dongambackend.domain.user.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun convertCreateUserReqDtoToEntity(createRequest: UserCreateRequest, passwordEncoder: PasswordEncoder) =
        User(
            createRequest.studentId,
            passwordEncoder.encode(createRequest.password),
            createRequest.nickname,
            createRequest.deviceToken
        )
}