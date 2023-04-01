package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.domain.post.dto.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.PostResponse
import com.kotlin.study.dongambackend.domain.post.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {
    @PostMapping
    fun createPost(postCreateRequest: PostCreateRequest): PostResponse {
        return postService.createPost(postCreateRequest)
    }
}