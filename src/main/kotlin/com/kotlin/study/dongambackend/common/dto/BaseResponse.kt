package com.kotlin.study.dongambackend.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

@JsonPropertyOrder("code", "success", "result")
data class BaseResponse<T>(
    val code: Int,
    val status: String,
    @JsonInclude(JsonInclude.Include.NON_NULL) // null일때 출력하지 않는다.
    val result: T?
) {

    // success
    constructor(status: ResponseStatusType) : this(status.code, status.statusMessage, null)
    constructor(status: ResponseStatusType, result: T) : this(status.code, status.statusMessage, result)
    // fail
    constructor(status: ResponseStatusType, isFailure: Boolean = false) : this(status.code, status.statusMessage,null)

    fun convert(): ResponseEntity<BaseResponse<T>> {
        val headers = HttpHeaders()
        headers["Content-Type"] = "application/json; charset=UTF-8"
        return ResponseEntity.status(code).headers(headers).body(this)
    }
}
