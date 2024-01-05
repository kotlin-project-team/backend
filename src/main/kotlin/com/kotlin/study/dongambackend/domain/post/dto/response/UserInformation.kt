package com.kotlin.study.dongambackend.domain.post.dto.response

import com.querydsl.core.annotations.QueryProjection

data class UserInformation @QueryProjection constructor(
    val id: Long,
    val studentId: String,
    val nickname: String
)