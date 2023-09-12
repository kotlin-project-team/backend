package com.kotlin.study.dongambackend.domain.comment.dto.response

data class CommentResponse(
    val id: Long,        // 댓글의 고유 식별자
    val userId: Long,    // 댓글 작성자의 고유 식별자
    val postId: Long,    // 댓글이 속한 게시물의 고유 식별자
    val content: String  // 댓글 내용
)