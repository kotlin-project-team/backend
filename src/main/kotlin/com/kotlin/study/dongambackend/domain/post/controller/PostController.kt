package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.post.validator.ValidateCategory
import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory
import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.GetPostByIdResponse
import com.kotlin.study.dongambackend.domain.post.service.PostService

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.net.URI

import java.util.*

import javax.validation.Valid

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPost(
        pageable: Pageable,
        @RequestParam(value = "category", required = true)
        @ValidateCategory(enumClass = BoardCategory::class) category: BoardCategory
    ): ResponseEntity<BaseResponse<GetAllPostByCategoryResponse>> {
        val posts = postService.getAllPost(pageable, category)
        return BaseResponse(ResponseStatusType.SUCCESS, posts).convert()
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<BaseResponse<GetPostByIdResponse?>> {
        val post = postService.getPostById(postId)
        return BaseResponse(ResponseStatusType.SUCCESS, post).convert()
    }

    @PostMapping
    fun createPost(@Valid @RequestBody postCreateRequest: PostCreateRequest): ResponseEntity<Unit> {
        val userId = 1L
        val postId = postService.createPost(postCreateRequest, userId)
        return ResponseEntity.created(URI.create("/api/post/${postId}")).build()
    }

    @PatchMapping("/{postId}")
    fun updatePost(
        @Valid @RequestBody postUpdateRequest: PostUpdateRequest,
        @PathVariable postId: Long
    ): ResponseEntity<BaseResponse<Unit>> {
        postService.updatePost(postUpdateRequest, postId)
        return BaseResponse<Unit>(ResponseStatusType.SUCCESS).convert()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<BaseResponse<Unit>> {
        postService.deletePost(postId)
        return BaseResponse<Unit>(ResponseStatusType.SUCCESS).convert()
    }

    @PostMapping("/like/{postId}")
    fun clickPostLike(@PathVariable postId: Long): ResponseEntity<Unit> {
        postService.clickPostLike(postId, 2L)
        return ResponseEntity.created(URI.create("/api/post/like/${postId}")).build()
    }
}