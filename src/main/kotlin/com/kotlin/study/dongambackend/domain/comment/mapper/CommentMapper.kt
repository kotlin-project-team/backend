package com.kotlin.study.dongambackend.domain.comment.mapper

import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import org.springframework.stereotype.Component

@Component
class CommentMapper {

    fun convertCommentsToResponses(results: List<Comment>): List<CommentResponse> {
        return results.map { comment ->
            CommentResponse(
                id = comment.id,
                userId = comment.userId,
                postId = comment.postId,
                content = comment.content
            )
        }
    }

}