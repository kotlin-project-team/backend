package com.kotlin.study.dongambackend.domain.user.controller

import com.kotlin.study.dongambackend.domain.user.dto.request.UserCreateRequest
import com.kotlin.study.dongambackend.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@Valid @RequestBody userCreateRequest: UserCreateRequest): ResponseEntity<Unit> {
        val userId = userService.createUser(userCreateRequest)
        return ResponseEntity.created(URI.create("/api/user/$userId")).build()
    }
}