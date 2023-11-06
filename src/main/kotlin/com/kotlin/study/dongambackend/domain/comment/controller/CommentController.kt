package com.kotlin.study.dongambackend.domain.comment.controller

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentSliceRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentReportResponse
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*


@RestController
@RequestMapping("/api/comment")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(
        @RequestBody commentCreateRequest: CommentCreateRequest
    ): ResponseEntity<Unit> {
        val commentId = commentService.createComment(commentCreateRequest)
        return ResponseEntity.created(URI.create("/api/comment/${commentId}")).build()
    }

    @PostMapping("/report/{commentId}")
    fun reportComment(
        @PathVariable commentId: Long,
        @RequestBody commentReportRequest: CommentReportRequest
    ): ResponseEntity<CommentReportResponse> {
        val reportId = commentService.reportComment(commentId, commentReportRequest)
        return ResponseEntity.created(URI.create("/api/comment/$reportId")).build()
    }

    @PatchMapping("/{commentId}")
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
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<BaseResponse<ResponseStatusType?>> {
        return try {
            commentService.deleteComment(commentId)
            BaseResponse<ResponseStatusType?>(ResponseStatusType.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatusType?>(e.status, false).convert()
        }
    }

    // TODO: 댓글 목록 조회 리스트 api
    @GetMapping
    fun getAllComment(@RequestParam commentId:Long, pageable: Pageable): ResponseEntity<Slice<Comment>> {
        val comments = commentService.getAllComment(commentId, pageable)
        return ResponseEntity.ok().body(comments)
    }


}