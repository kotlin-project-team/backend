package com.kotlin.study.dongambackend.domain.comment.dto.request

data class CommentReportRequest(
    val reason: String,
    val isSolved: Boolean
) {
}