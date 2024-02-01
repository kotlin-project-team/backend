package com.kotlin.study.dongambackend.domain.user.service

import com.kotlin.study.dongambackend.domain.user.dto.request.SignInRequest
import com.kotlin.study.dongambackend.domain.user.dto.request.UpdateNicknameRequest
import com.kotlin.study.dongambackend.domain.user.dto.request.UpdatePasswordRequest
import com.kotlin.study.dongambackend.domain.user.dto.request.CreateUserRequest
import com.kotlin.study.dongambackend.domain.user.dto.response.MyInformationResponse
import com.kotlin.study.dongambackend.domain.user.dto.response.SignInResponse
import com.kotlin.study.dongambackend.domain.user.exception.PasswordNotMisMatchException
import com.kotlin.study.dongambackend.domain.user.mapper.UserMapper
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository
import com.kotlin.study.dongambackend.security.util.TokenProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userMapper: UserMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {

    fun createUser(userCreateRequest: CreateUserRequest): Long? {
        val user = userMapper.convertCreateUserReqDtoToEntity(userCreateRequest, passwordEncoder)
        return userRepository.save(user).id
    }

    @Transactional(readOnly = true)
    fun getMyInformation(userId: Long): MyInformationResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()
        return MyInformationResponse(user.studentId, user.nickname)
    }

    @Transactional(readOnly = true)
    fun checkPasswordForMyPage(password: String, userId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()

        if (!passwordEncoder.matches(password, user.password))
            throw PasswordNotMisMatchException("비밀번호가 일치하지 않습니다.")
    }

    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest, userId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()

        if (!passwordEncoder.matches(updatePasswordRequest.oldPassword, user.password))
            throw PasswordNotMisMatchException("비밀번호가 일치하지 않습니다.")

        user.updatePassword(updatePasswordRequest.newPassword, passwordEncoder)
        userRepository.save(user)
    }

    fun updateNickname(updateNicknameRequest: UpdateNicknameRequest, userId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()

        user.updateNickname(updateNicknameRequest.nickname)
        userRepository.save(user)
    }

    fun deleteUser(userId: Long) {
        userRepository.deleteById(userId)
    }

    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val user = userRepository.findByStudentId(signInRequest.studentId)
            ?: throw NoSuchElementException()

        if (!passwordEncoder.matches(signInRequest.password, user.password))
            throw PasswordNotMisMatchException("비밀번호가 일치하지 않습니다.")

        val accessToken = tokenProvider.createAccessToken(user.role.toString(), user.id!!)
        val refreshToken = tokenProvider.createRefreshToken(user.role.toString(), user.id!!)

        return SignInResponse(
            user.id!!,
            user.studentId,
            user.nickname,
            accessToken,
            refreshToken
        )
    }
}