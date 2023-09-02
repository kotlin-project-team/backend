package com.kotlin.study.dongambackend.domain.post.dto.request

data class PostCreateRequest (
    var title: String,
    var content: String,
    var category: String,
)