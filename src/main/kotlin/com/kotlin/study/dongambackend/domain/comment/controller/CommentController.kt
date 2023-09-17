package com.kotlin.study.dongambackend.domain.comment.controller

import com.kotlin.study.dongambackend.common.config.BaseResponse
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentReportRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentReportResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
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
    ): ResponseEntity<Void> {
        val commentId = commentService.createComment(commentCreateRequest)
        return ResponseEntity.created(URI.create("/api/comment/${commentId}")).build()
    }

    @PostMapping("/report/{commentId}")
    fun reportComment(
        @RequestBody commentReportRequest: CommentReportRequest
    ): ResponseEntity<CommentReportResponse> {
        val reportId = commentService.reportComment(commentReportRequest)
        return ResponseEntity.created(URI.create("/api/comment/${reportId}")).build()
    }


    @GetMapping("/{commentId}")
    fun getCommentById(@PathVariable commentId: Long): ResponseEntity<Optional<Comment>> {
        val comment = commentService.getCommentById(commentId)
        return ResponseEntity.ok().body(comment)
    }

    @PatchMapping("/{commentId}")
    fun updateComment(
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
        @PathVariable commentId: Long
    ): ResponseEntity<BaseResponse> {
        val result = commentService.updateComment(commentUpdateRequest, commentId)
        return ResponseEntity.status(result.code).body(result)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(commentId)
        return ResponseEntity.ok().build()
    }


}