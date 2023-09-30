package com.kotlin.study.dongambackend.domain.post.dto.request

data class PostCreateRequest (
    val title: String,
    val content: String,
    val category: String,
)