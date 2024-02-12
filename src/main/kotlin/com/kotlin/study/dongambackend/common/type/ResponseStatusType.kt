package com.kotlin.study.dongambackend.common.type

enum class ResponseStatusType(val code: Int, val statusMessage: String) {
    SUCCESS(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    TOKEN_MISMATCH(403, "Forbidden"),
    ID_NOT_FOUND(404, "postId Not Found"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    PASSWORD_MISMATCH(400, "Password Mismatch")
}