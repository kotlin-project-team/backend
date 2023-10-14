package com.kotlin.study.dongambackend.domain.post.dto.request

import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostCreateRequest (
    @field:NotBlank(message = "lakjdflakdjflskdjfshfksh")
    val title: String?,

    @field:NotEmpty(message = "alskdalskjd")
    val content: String?,

    val category: BoardCategoryType,
)