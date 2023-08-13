package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.PostResponse
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPost(pageable: Pageable): Page<Post> {
        return postService.getAllPost(pageable)
    }

    @PostMapping
    fun createPost(@RequestBody postCreateRequest: PostCreateRequest): Post {
        return postService.createPost(postCreateRequest)
    }

    @PatchMapping("/{post_id}")
    fun updatePost(@RequestBody postUpdateRequest: PostUpdateRequest, @PathVariable("post_id") postId: Long): Post {
        return postService.updatePost(postUpdateRequest, postId)
    }

    @DeleteMapping("/{post_id}")
    fun deletePost(@PathVariable("post_id") postId: Long): String {
        postService.deletePost(postId)
        return "OK"
    }
}