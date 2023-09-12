package com.kotlin.study.dongambackend.domain.comment.controller

import com.kotlin.study.dongambackend.common.config.BaseResponse
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentCreateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentReportResponse
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/comment")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(
        @RequestBody commentCreateRequest: CommentCreateRequest
    ): ResponseEntity<BaseResponse> {
        val result = commentService.createComment(commentCreateRequest)

        return ResponseEntity.created(URI.create("/api/comment/${result.id}")).build()
//        return ResponseEntity.status(result.code).body(result)
    }

    // report 추후 수정
    @PostMapping("/report/{commentId}")
//    fun reportComment(
//        @RequestBody commentCreateRequest: CommentCreateRequest
//    ): ResponseEntity<CommentReportResponse> {
//        val result = commentService.reportComment(commentCreateRequest)
//        return ResponseEntity.status(result.code).body(result)
//    }

    @PatchMapping("/{commentId}")
    fun updateComment(
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
        @PathVariable commentId: Long
    ): ResponseEntity<BaseResponse> {
        val result = commentService.updateComment(commentUpdateRequest, commentId)
        return ResponseEntity.status(result.code).body(result)
    }

    // TODO: delete 정상 처리되는 코드로 수정.
    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<BaseResponse> {
        val result = commentService.deleteComment(commentId)
        return ResponseEntity.ok().build()
    }


}