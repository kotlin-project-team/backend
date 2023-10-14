package com.kotlin.study.dongambackend.domain.post.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostUpdateRequest (
    @field:NotBlank(message = "title is nessaasdj")
    val title: String?,

    @field:NotEmpty(message = "내용을 입력해주세요.")
    val content: String?,
)