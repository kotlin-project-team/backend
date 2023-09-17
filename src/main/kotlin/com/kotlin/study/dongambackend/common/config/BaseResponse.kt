package com.kotlin.study.dongambackend.common.config

data class BaseResponse(
    val code: Int,
    val status: String
) {
    constructor(status: ResponseStatus) : this(status.code, status.status)
}
