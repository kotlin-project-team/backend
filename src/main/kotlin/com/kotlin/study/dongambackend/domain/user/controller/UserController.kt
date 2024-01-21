package com.kotlin.study.dongambackend.domain.user.controller

import com.kotlin.study.dongambackend.domain.user.dto.request.*
import com.kotlin.study.dongambackend.domain.user.dto.response.MyInformationResponse
import com.kotlin.study.dongambackend.domain.user.dto.response.SignInResponse
import com.kotlin.study.dongambackend.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping("/sign-up")
    fun createUser(@Valid @RequestBody userCreateRequest: CreateUserRequest): ResponseEntity<Unit> {
        val userId = userService.createUser(userCreateRequest)
        return ResponseEntity.created(URI.create("/api/user/$userId")).build()
    }

    @GetMapping
    fun getMyInformation(): ResponseEntity<MyInformationResponse> {
        val userId = 1L   // TODO: 추후 Token으로 수정
        val result = userService.getMyInformation(userId)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/my-page/password")
    fun checkPasswordForMyPage(
        @RequestBody checkPasswordForMyPageRequest: CheckPasswordForMyPageRequest
    ): ResponseEntity<Unit> {
        val userId = 1L
        userService.checkPasswordForMyPage(checkPasswordForMyPageRequest.password, userId);
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/password")
    fun updatePassword(@RequestBody @Valid updatePasswordRequest: UpdatePasswordRequest): ResponseEntity<Unit> {
        val userId = 1L
        userService.updatePassword(updatePasswordRequest, userId)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/nickname")
    fun updateNickname(@RequestBody updateNicknameRequest: UpdateNicknameRequest): ResponseEntity<Unit> {
        val userId = 1L
        userService.updateNickname(updateNicknameRequest, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteUser(): ResponseEntity<Unit> {
        val userId = 1L
        userService.deleteUser(userId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        val response = userService.signIn(signInRequest)
        return ResponseEntity.ok().body(response)
    }
}