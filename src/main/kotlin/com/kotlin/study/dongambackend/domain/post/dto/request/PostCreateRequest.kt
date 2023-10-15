package com.kotlin.study.dongambackend.domain.post.dto.request

import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostCreateRequest (
    @field:NotBlank(message = "제목 값은 필수입니다.")
    val title: String?,

    @field:NotEmpty(message = "내용을 입력해주세요.")
    val content: String?,

    val category: BoardCategoryType,
)