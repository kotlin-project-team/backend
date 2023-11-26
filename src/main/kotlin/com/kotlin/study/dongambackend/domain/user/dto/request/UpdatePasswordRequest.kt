package com.kotlin.study.dongambackend.domain.user.dto.request

import javax.validation.constraints.Pattern

data class UpdatePasswordRequest(
    val oldPassword: String,

    @field:Pattern(
        regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
        message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
    )
    val newPassword: String,
)