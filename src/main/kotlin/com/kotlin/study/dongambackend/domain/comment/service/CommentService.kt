package com.kotlin.study.dongambackend.domain.comment.service

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.common.type.ResponseStatus
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
        commentRepository.save(comment)

        return comment.id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): Comment {
        val comment = commentRepository.findById(commentId).orElseGet { throw BaseException(ResponseStatus.BAD_REQUEST) }
        // TODO: Unauthorized 및 ForbiddenToken 처리
        comment.postId ?: throw BaseException(ResponseStatus.NOT_FOUND)
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return comment
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId).orElseThrow { BaseException(ResponseStatus.BAD_REQUEST) }
        // TODO: Unauthorized 및 ForbiddenToken 처리
        val commentIdToDelete = comment.id ?: throw BaseException(ResponseStatus.NOT_FOUND)
        commentRepository.deleteById(commentIdToDelete)
    }

    fun reportComment(commentId: Long, commentReportRequest: CommentReportRequest): Long? {
        val reportComment = ReportComment(commentReportRequest.reason, commentReportRequest.isSolved, commentId)
        // TODO: 자신의 댓글인 경우 / Unauthorized 처리
        commentReportRepository.save(reportComment)

        return reportComment.id
    }
}