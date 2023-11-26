package com.kotlin.study.dongambackend.domain.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserCreateRequest(
    @field:NotBlank(message = "학번 값은 필수입니다.")
    val studentId: String,

    @field:NotBlank(message = "비밀번호 값은 필수입니다.")
    @field:Pattern(
        regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
        message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
    )
    val password: String,

    @field:NotBlank(message = "닉네임은 필수입니다.")
    val nickname: String,

    @field:NotBlank(message = "디바이스 토큰 값은 필수입니다.")
    val deviceToken: String
)