package com.kotlin.study.dongambackend.domain.post.dto.request

import com.kotlin.study.dongambackend.common.type.BoardCategoryType

data class PostCreateRequest (
    val title: String,
    val content: String,
    val category: BoardCategoryType,
)