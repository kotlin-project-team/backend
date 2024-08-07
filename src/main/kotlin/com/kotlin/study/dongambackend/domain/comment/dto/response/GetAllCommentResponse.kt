package com.kotlin.study.dongambackend.domain.comment.dto.response

data class GetAllCommentResponse (
    val comments: List<FindAllCommentResponse>,
    val commentCount: Int
)