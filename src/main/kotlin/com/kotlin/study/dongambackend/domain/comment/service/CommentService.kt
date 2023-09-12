package com.kotlin.study.dongambackend.domain.comment.service

import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.common.config.BaseResponse
import com.kotlin.study.dongambackend.common.config.ResponseStatus
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class CommentService(private val commentRepository: CommentRepository) {

    fun createComment(commentCreateRequest: CommentCreateRequest): Comment {
        val comment = Comment(commentCreateRequest.content)
        return commentRepository.save(comment)

//        return BaseResponse(ResponseStatus.SUCCESS)
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): BaseResponse {
        val comment = commentRepository.findById(commentId).get()
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return BaseResponse(ResponseStatus.SUCCESS)
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId).get()
        comment.deleteComment()
        commentRepository.save(comment)
    }

    fun reportComment(commentCreateRequest: CommentCreateRequest): BaseResponse {
        val comment = Comment(commentCreateRequest.content)
        commentRepository.save(comment)

        return BaseResponse(ResponseStatus.SUCCESS)
    }
}