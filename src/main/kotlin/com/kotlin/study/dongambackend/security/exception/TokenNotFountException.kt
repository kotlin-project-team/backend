package com.kotlin.study.dongambackend.security.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TokenNotFountException : Exception("Token이 Header에 존재하지 않습니다.")