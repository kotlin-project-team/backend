package com.kotlin.study.dongambackend.domain.comment.dto.request

import javax.validation.constraints.NotEmpty

data class CommentCreateRequest (
    @field:NotEmpty(message = "내용을 입력해주세요.")
    var content: String,
)
