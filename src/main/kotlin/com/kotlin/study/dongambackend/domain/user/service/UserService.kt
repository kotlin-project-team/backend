package com.kotlin.study.dongambackend.domain.user.service

import com.kotlin.study.dongambackend.domain.user.dto.request.UserCreateRequest
import com.kotlin.study.dongambackend.domain.user.mapper.UserMapper
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userMapper: UserMapper, private val userRepository: UserRepository) {

    fun createUser(userCreateRequest: UserCreateRequest): Long? {
        val user = userMapper.convertCreateUserReqDtoToEntity(userCreateRequest)
        return userRepository.save(user).id
    }
}