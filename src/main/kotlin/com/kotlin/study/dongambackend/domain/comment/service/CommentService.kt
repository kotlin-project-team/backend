package com.kotlin.study.dongambackend.domain.comment.service

import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.common.config.BaseResponse
import com.kotlin.study.dongambackend.common.config.ResponseStatus
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.ReportComment
import com.kotlin.study.dongambackend.domain.comment.repository.CommentReportRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.util.*

@Service
@Slf4j
class CommentService(private val commentRepository: CommentRepository, private val commentReportRepository: CommentReportRepository) {

    fun createComment(commentCreateRequest: CommentCreateRequest): Long? {
        val comment = Comment(commentCreateRequest.content)
        commentRepository.save(comment)

        return comment.id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): BaseResponse {
        val comment = commentRepository.findById(commentId).get()
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return BaseResponse(ResponseStatus.SUCCESS)
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId).get()
        commentRepository.deleteById(comment.id!!)
    }

    fun reportComment(commentReportRequest: CommentReportRequest): Long? {
        val reportComment = ReportComment(commentReportRequest.reason, commentReportRequest.isSolved)
        commentReportRepository.save(reportComment)

        return reportComment.id
    }

    fun getCommentById(commentId: Long): Optional<Comment> {

        BaseResponse(ResponseStatus.SUCCESS)
        return commentRepository.findById(commentId)
    }
}