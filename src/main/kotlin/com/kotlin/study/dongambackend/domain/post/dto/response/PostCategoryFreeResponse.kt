package com.kotlin.study.dongambackend.domain.post.dto.response

import com.kotlin.study.dongambackend.domain.user.entity.User
import com.querydsl.core.annotations.QueryProjection

data class PostCategoryFreeResponse @QueryProjection constructor(
    val id: Long,
    val user: User,
    val title: String,
    val content: String,
    val likes: Int,
    val views: Int
)