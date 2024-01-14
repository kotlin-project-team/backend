package com.kotlin.study.dongambackend.domain.comment.dto.response

import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.user.entity.User

data class CommentResponse(
    val id: Long?,
    val userId: User,
    val postId: Post,
    val content: String?
)