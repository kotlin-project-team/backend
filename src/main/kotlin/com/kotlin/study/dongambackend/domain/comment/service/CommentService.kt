package com.kotlin.study.dongambackend.domain.comment.service


import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentSliceRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.ReportComment
import com.kotlin.study.dongambackend.domain.comment.repository.CommentQueryDslRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentReportRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentReportRepository: CommentReportRepository,
    val commentQueryDslRepository: CommentQueryDslRepository
) {
    @Transactional(readOnly = true)
    fun getAllComment(commentId: Long, pageable: Pageable): Slice<Comment> {
        return commentQueryDslRepository.searchCommentsBySlice(commentId, pageable)
    }

    fun createComment(commentCreateRequest: CommentCreateRequest): Long? {
        val comment = Comment(commentCreateRequest.content)
        commentRepository.save(comment)

        // TODO: Unauthorized 처리
        val commentId = comment.postId?.let { commentRepository.findById(it).orElseThrow { BaseException(ResponseStatusType.NOT_FOUND) } }

        return comment.id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): Comment {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw BaseException(ResponseStatusType.BAD_REQUEST)
        
        // TODO: Unauthorized 및 ForbiddenToken 처리
        comment.postId ?: throw BaseException(ResponseStatusType.NOT_FOUND)
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return comment
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw BaseException(ResponseStatusType.NOT_FOUND)

        // TODO: Unauthorized 및 ForbiddenToken 처리
        val commentIdToDelete = comment.id ?: throw BaseException(ResponseStatusType.NOT_FOUND)
        commentRepository.deleteById(commentIdToDelete)
    }

    fun reportComment(commentId: Long, commentReportRequest: CommentReportRequest): Long? {
        val reportComment = ReportComment(commentReportRequest.reason, commentReportRequest.isSolved, commentId)
        // TODO: 자신의 댓글인 경우 / Unauthorized 처리
        commentReportRepository.save(reportComment)

        return reportComment.id
    }
}