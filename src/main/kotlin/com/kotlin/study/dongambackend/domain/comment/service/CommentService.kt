package com.kotlin.study.dongambackend.domain.comment.service


import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.ReportComment
import com.kotlin.study.dongambackend.domain.comment.mapper.CommentMapper
import com.kotlin.study.dongambackend.domain.comment.repository.CommentQueryDslRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentReportRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentReportRepository: CommentReportRepository,
    private val commentQueryDslRepository: CommentQueryDslRepository,
    private val commentMapper: CommentMapper,
    private val userRepository: UserRepository
    //postReposistory
) {
    @Transactional(readOnly = true)
    fun getAllComment(commentId: Long, pageable: Pageable): Slice<CommentResponse> {
        val comments = commentQueryDslRepository.searchCommentsBySlice(commentId, pageable)
        val commentResponses = commentMapper.convertCommentsToResponses(comments)

        return checkLastPage(pageable, commentResponses)
    }

    fun createComment(commentCreateRequest: CommentCreateRequest, userId: Long? = 1L): Long? {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()

        val comment = commentMapper.convertCreateCommentReqDtoToEntity(user, commentCreateRequest)
        return commentRepository.save(comment).id
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
        // TODO: Unauthorized 처리
        commentReportRepository.save(reportComment)

        return reportComment.id
    }

    // -- 메서드 --

    // 무한 스크롤
    private fun checkLastPage(pageable: Pageable, results: List<CommentResponse>): Slice<CommentResponse> {
        val hasNext = results.size > pageable.pageSize
        val mutableResults = if (hasNext) results.subList(0, pageable.pageSize) else results.toMutableList()

        return SliceImpl(mutableResults, pageable, hasNext)
    }
}