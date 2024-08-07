package com.kotlin.study.dongambackend.domain.comment.service

import com.kotlin.study.dongambackend.common.exception.common.NotFoundException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.dto.response.FindAllCommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.ReportComment
import com.kotlin.study.dongambackend.domain.comment.mapper.CommentMapper
import com.kotlin.study.dongambackend.domain.comment.repository.CommentQueryDslRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentReportRepository
import com.kotlin.study.dongambackend.domain.comment.repository.CommentRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
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
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun getAllComment(postId: Long, commentId: Long, pageable: Pageable): Slice<FindAllCommentResponse> {
        val comments = commentQueryDslRepository.searchCommentsByPostId(postId, commentId, pageable)
//        val commentResponses = commentMapper.convertCommentsToResponses(comments)

        return checkLastPage(pageable, comments)
    }

    fun createComment(commentCreateRequest: CommentCreateRequest, postId: Long, userId: Long?): Long? {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException(ResponseStatusType.COMMENT_NOT_FOUND)
        val post = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException(ResponseStatusType.COMMENT_NOT_FOUND)

        val comment = commentMapper.convertCreateCommentReqDtoToEntity(user, post, commentCreateRequest)
        return commentRepository.save(comment).id
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest, commentId: Long): Comment {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw NotFoundException(ResponseStatusType.COMMENT_NOT_FOUND)

        // TODO: Unauthorized 및 ForbiddenToken 처리
        comment.updateComment(commentUpdateRequest)
        commentRepository.save(comment)

        return comment
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw NotFoundException(ResponseStatusType.COMMENT_NOT_FOUND)

        // TODO: Unauthorized 및 ForbiddenToken 처리
        val commentIdToDelete = comment.id ?: throw NotFoundException(ResponseStatusType.COMMENT_NOT_FOUND)
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
    private fun checkLastPage(pageable: Pageable, results: List<FindAllCommentResponse>): Slice<FindAllCommentResponse> {
        val hasNext = results.size > pageable.pageSize
        val mutableResults = if (hasNext) results.subList(0, pageable.pageSize) else results.toMutableList()

        return SliceImpl(mutableResults, pageable, hasNext)
    }

}