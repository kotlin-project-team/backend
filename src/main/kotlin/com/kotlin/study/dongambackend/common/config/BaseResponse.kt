package com.kotlin.study.dongambackend.common.config

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

data class BaseResponse(
    val code: Int,
    val status: String,
) {
    constructor(status: ResponseStatus) : this(status.code, status.status)
}
