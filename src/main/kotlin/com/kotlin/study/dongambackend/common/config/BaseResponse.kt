package com.kotlin.study.dongambackend.common.config

data class BaseResponse<T>(
    val code: Int,
    val status: String,

    private var result: T? = null
) {
    constructor(status: ResponseStatus, result: T?) : this(status.code, status.status) {
        this.result = result
    }
}