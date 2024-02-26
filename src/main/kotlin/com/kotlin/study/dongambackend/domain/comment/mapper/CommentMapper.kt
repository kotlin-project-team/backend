package com.kotlin.study.dongambackend.domain.comment.mapper

import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.dto.response.FindAllCommentResponse
import com.kotlin.study.dongambackend.domain.comment.dto.response.GetAllCommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
class CommentMapper {

    fun convertCreateCommentReqDtoToEntity(user: User, post: Post, createRequest: CommentCreateRequest): Comment {
        return Comment(user, post, createRequest.content)
    }

    fun toGetAllCommentResponse(comments: List<FindAllCommentResponse>, postCount: Int): GetAllCommentResponse {
        return GetAllCommentResponse(comments, postCount)
    }
}