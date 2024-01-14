package com.kotlin.study.dongambackend.domain.comment.dto.request

import com.kotlin.study.dongambackend.domain.post.entity.Post
import javax.validation.constraints.NotEmpty

data class CommentCreateRequest (
    val postId: Post,
    
    @field:NotEmpty(message = "내용을 입력해주세요.")
    var content: String,
) {
}