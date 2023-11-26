package com.kotlin.study.dongambackend.domain.user.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PasswordNotMisMatchException(message: String) : Exception(message)