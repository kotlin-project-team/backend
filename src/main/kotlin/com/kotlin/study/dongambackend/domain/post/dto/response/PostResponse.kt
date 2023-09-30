package com.kotlin.study.dongambackend.domain.post.dto.response

data class PostResponse(
    val id: Long,
    val userId: Long,   // TODO: Nested Object로 작성자 정보 전달하기
    val title: String,
    val content: String,
    val category: String,
    val likes: Int,
    val views: Int
)