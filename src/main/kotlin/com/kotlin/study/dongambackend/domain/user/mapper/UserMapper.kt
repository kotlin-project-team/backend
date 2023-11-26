package com.kotlin.study.dongambackend.domain.user.mapper

import com.kotlin.study.dongambackend.domain.user.dto.request.UserCreateRequest
import com.kotlin.study.dongambackend.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun convertCreateUserReqDtoToEntity(createRequest: UserCreateRequest): User {
        return User(createRequest.studentId, createRequest.password, createRequest.nickname, createRequest.deviceToken)
    }
}