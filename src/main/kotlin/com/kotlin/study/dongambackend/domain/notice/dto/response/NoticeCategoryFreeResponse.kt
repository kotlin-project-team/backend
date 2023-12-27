package com.kotlin.study.dongambackend.domain.notice.dto.response

import com.querydsl.core.annotations.QueryProjection

data class NoticeCategoryFreeResponse @QueryProjection constructor(
    val id: Long,
    val title: String,
    val content: String
)