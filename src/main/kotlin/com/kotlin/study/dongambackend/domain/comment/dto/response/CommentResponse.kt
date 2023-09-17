package com.kotlin.study.dongambackend.domain.comment.dto.response

data class CommentResponse(
    val id: Long,
    val userId: Long,
    val postId: Long,
    val content: String
)