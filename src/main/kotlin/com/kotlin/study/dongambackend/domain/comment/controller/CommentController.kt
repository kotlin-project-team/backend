package com.kotlin.study.dongambackend.domain.comment.controller

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
import com.kotlin.study.dongambackend.domain.user.entity.User
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*


@RestController
@RequestMapping("/api")
class CommentController(private val commentService: CommentService) {

    @PostMapping("/post/comment")
    fun createComment(
        @RequestBody commentCreateRequest: CommentCreateRequest, @AuthenticationPrincipal user: User
        ): ResponseEntity<Unit> {
        val commentId = commentService.createComment(commentCreateRequest, user.id)
        return ResponseEntity.created(URI.create("/api/comment/${commentId}")).build()
    }


    @PostMapping("/comment/report/{commentId}")
    fun reportComment(
        @PathVariable commentId: Long,
        @RequestBody commentReportRequest: CommentReportRequest
    ): ResponseEntity<Unit> {
        val reportId = commentService.reportComment(commentId, commentReportRequest)
        return ResponseEntity.created(URI.create("/api/comment/$reportId")).build()
    }

    @PatchMapping("/comment/{commentId}")
    fun updateComment(
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
        @PathVariable commentId: Long
    ): ResponseEntity<BaseResponse<ResponseStatusType?>> {
        return try {
            val result = commentService.updateComment(commentUpdateRequest, commentId)
            BaseResponse<ResponseStatusType?>(ResponseStatusType.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatusType?>(e.status, false).convert()
        }
    }

    @DeleteMapping("/comment/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<BaseResponse<ResponseStatusType?>> {
        return try {
            commentService.deleteComment(commentId)
            BaseResponse<ResponseStatusType?>(ResponseStatusType.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatusType?>(e.status, false).convert()
        }
    }

    @GetMapping("/comment")
    fun getAllComment(@RequestParam lastCommentId: Long, pageable: Pageable): ResponseEntity<List<CommentResponse>> {
        val commentsSlice = commentService.getAllComment(lastCommentId, pageable)
        val comments = commentsSlice.content // Slice에서 content만 추출
        return ResponseEntity.ok().body(comments)
    }


}