package com.kotlin.study.dongambackend.domain.post.dto.response

data class GetAllPostByCategoryResponse (
    val posts: List<FindAllPostByCategory>,
    val postCount: Int
)