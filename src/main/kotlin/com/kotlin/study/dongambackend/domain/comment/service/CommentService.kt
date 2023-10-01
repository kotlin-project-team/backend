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
import java.io.IOException

@Service
@Slf4j
class CommentService(private val commentRepository: CommentRepository, private val commentReportRepository: CommentReportRepository) {

    fun createComment(commentCreateRequest: CommentCreateRequest): Long? {
        val comment = Comment(commentCreateRequest.content)
        commentRepository.save(comment)

        return comment.id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): Comment {
        val comment = commentRepository.findById(commentId).get()
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return comment
    }

    fun deleteComment(commentId: Long) {
        try {
            val comment = commentRepository.findById(commentId).get()
            // TODO: !!가 없는 경우 처리
            commentRepository.deleteById(comment.id!!)
        } catch (e: IOException) {
            // TODO: IOException이 아닌 로그인 여부에 따른 처리로 변경.
            throw BaseException(ResponseStatus.UNAUTHORIZED);
        }
    }

    fun reportComment(commentId: Long, commentReportRequest: CommentReportRequest): Long? {
        val reportComment = ReportComment(commentReportRequest.reason, commentReportRequest.isSolved, commentId)
        commentReportRepository.save(reportComment)

        return reportComment.id
    }
}