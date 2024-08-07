package com.kotlin.study.dongambackend.domain.comment.dto.response

import com.kotlin.study.dongambackend.domain.post.dto.response.UserInformation
import com.querydsl.core.annotations.QueryProjection

data class FindAllCommentResponse @QueryProjection constructor(
    val id: Long,
    val user: UserInformation,
    val postId: Long,
    val content: String?
)