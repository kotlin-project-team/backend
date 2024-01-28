package com.kotlin.study.dongambackend.domain.post.dto.response

import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory

data class GetPostByIdResponse(
    val id: Long,
    val user: UserInformation,
    val category: BoardCategory,
    val title: String,
    val content: String,
    val likes: Int,
    val views: Int
)