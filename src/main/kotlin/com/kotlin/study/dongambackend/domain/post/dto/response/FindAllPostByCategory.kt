package com.kotlin.study.dongambackend.domain.post.dto.response

import com.querydsl.core.annotations.QueryProjection

data class FindAllPostByCategory @QueryProjection constructor(
    val id: Long,
    val user: UserInformation,
    val title: String,
    val content: String,
    val likes: Int,
    val views: Int
)