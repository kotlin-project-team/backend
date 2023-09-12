package com.kotlin.study.dongambackend.common.config

enum class ResponseStatus(val code: Int, val status: String) {
    // comment status
    SUCCESS(201, "Created"),
    BAD_REQUEST(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}