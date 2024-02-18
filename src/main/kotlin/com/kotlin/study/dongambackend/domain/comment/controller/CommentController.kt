package com.kotlin.study.dongambackend.domain.comment.controller

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
import com.kotlin.study.dongambackend.domain.user.entity.User
import org.slf4j.LoggerFactory

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

import java.net.URI



@RestController
@RequestMapping("/api/post/{postId}/comment")
class CommentController(private val commentService: CommentService) {
    private val log = LoggerFactory.getLogger(CommentController::class.java)
    @PostMapping
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody commentCreateRequest: CommentCreateRequest,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Unit> {
        val commentId = commentService.createComment(commentCreateRequest, postId, user.id)
        return ResponseEntity.created(URI.create("/api/comment/${commentId}")).build()
    }

    @PostMapping("/report/{commentId}")
    fun reportComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentReportRequest: CommentReportRequest
    ): ResponseEntity<Unit> {
        val reportId = commentService.reportComment(commentId, commentReportRequest)
        return ResponseEntity.created(URI.create("/api/comment/$reportId")).build()
    }

    @PatchMapping("/{commentId}")
    fun updateComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
    ): ResponseEntity<BaseResponse<ResponseStatusType?>> {
        commentService.updateComment(commentUpdateRequest, commentId)
        return BaseResponse<ResponseStatusType?>(ResponseStatusType.SUCCESS).convert()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<BaseResponse<ResponseStatusType?>> {
        commentService.deleteComment(commentId)
        return BaseResponse<ResponseStatusType?>(ResponseStatusType.SUCCESS).convert()
    }

    @GetMapping
    fun getAllComment(
        @PathVariable postId: Long,
        @RequestParam lastCommentId: Long,
        pageable: Pageable
    ): ResponseEntity<List<CommentResponse>> {
        val commentsSlice = commentService.getAllComment(lastCommentId, pageable)
        val comments = commentsSlice.content // Slice에서 content만 추출
        return ResponseEntity.ok().body(comments)
    }
}