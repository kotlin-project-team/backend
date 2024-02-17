package com.kotlin.study.dongambackend.domain.notice.dto.response

data class GetNoticeByIdResponse (
    val id: Long,
    val title: String,
    val content: String?
)