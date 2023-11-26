package com.kotlin.study.dongambackend.domain.user.service

import com.kotlin.study.dongambackend.domain.user.dto.request.UserCreateRequest
import com.kotlin.study.dongambackend.domain.user.dto.response.MyInformationResponse
import com.kotlin.study.dongambackend.domain.user.mapper.UserMapper
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userMapper: UserMapper, private val userRepository: UserRepository) {

    fun createUser(userCreateRequest: UserCreateRequest): Long? {
        val user = userMapper.convertCreateUserReqDtoToEntity(userCreateRequest)
        return userRepository.save(user).id
    }

    @Transactional(readOnly = true)
    fun getMyInformation(userId: Long): MyInformationResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()
        return MyInformationResponse(user.studentId, user.nickname)
    }
}