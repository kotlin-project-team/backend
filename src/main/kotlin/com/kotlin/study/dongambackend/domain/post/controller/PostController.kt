package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPost(pageable: Pageable): ResponseEntity<List<Post>> {
        val posts = postService.getAllPost(pageable)
        return ResponseEntity.ok().body(posts)
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<Post> {
        val post = postService.getPostById(postId)
        return ResponseEntity.ok().body(post)
    }

    @PostMapping
    fun createPost(@RequestBody postCreateRequest: PostCreateRequest): ResponseEntity<Unit> {
        val userId = 1L
        val postId = postService.createPost(postCreateRequest, userId)
        return ResponseEntity.created(URI.create("/api/post/${postId}")).build()
    }

    @PatchMapping("/{postId}")
    fun updatePost(@RequestBody postUpdateRequest: PostUpdateRequest, @PathVariable postId: Long): ResponseEntity<Unit> {
        postService.updatePost(postUpdateRequest, postId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/like/{postId}")
    fun clickPostLike(@PathVariable postId: Long): ResponseEntity<Unit> {
        postService.clickPostLike(postId, 1L)
        return ResponseEntity.ok().build()
    }
}