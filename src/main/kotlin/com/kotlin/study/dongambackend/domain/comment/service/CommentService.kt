package com.kotlin.study.dongambackend.domain.comment.service

import com.kotlin.study.dongambackend.common.config.BaseException
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.common.config.ResponseStatus
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.ReportComment
import com.kotlin.study.dongambackend.domain.comment.repository.CommentReportRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class CommentService(private val commentRepository: CommentRepository, private val commentReportRepository: CommentReportRepository) {

    fun createComment(commentCreateRequest: CommentCreateRequest): Long? {
        val comment = Comment(commentCreateRequest.content)
        // TODO: Unauthorized 처리
        comment.postId ?: throw BaseException(ResponseStatus.NOT_FOUND)
        try {
            commentRepository.save(comment)
        } catch (e: BaseException) {
            throw BaseException(ResponseStatus.INTERNAL_SERVER_ERROR)
        }
        return comment.id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): Comment {
        val comment = commentRepository.findById(commentId).orElseGet { throw BaseException(ResponseStatus.BAD_REQUEST) }
        // TODO: Unauthorized 및 ForbiddenToken 처리
        comment.postId ?: throw BaseException(ResponseStatus.NOT_FOUND)
        comment.updateComment(commentUpdateRequest)
        try {
            commentRepository.save(comment)
        } catch (e: BaseException) {
            throw BaseException(ResponseStatus.INTERNAL_SERVER_ERROR)
        }

        return comment
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId).orElseGet { throw BaseException(ResponseStatus.BAD_REQUEST) }
        // TODO: Unauthorized 및 ForbiddenToken 처리
        try {
            val commentId = comment.id ?: throw BaseException(ResponseStatus.NOT_FOUND)
            commentRepository.deleteById(commentId)
        } catch (e: BaseException) {
            throw BaseException(ResponseStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun reportComment(commentId: Long, commentReportRequest: CommentReportRequest): Long? {
        val reportComment = ReportComment(commentReportRequest.reason, commentReportRequest.isSolved, commentId)
        // TODO: 자신의 댓글인 경우 / Unauthorized 처리
        try {
            commentReportRepository.save(reportComment)
        } catch (e: BaseException) {
            throw BaseException(ResponseStatus.INTERNAL_SERVER_ERROR)
        }
        return reportComment.id
    }
}