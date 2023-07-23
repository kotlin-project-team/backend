package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.PostResponse
import com.kotlin.study.dongambackend.domain.post.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {
    @PostMapping
    fun createPost(@RequestBody postCreateRequest: PostCreateRequest): PostResponse {
        // @RequestBody 어노테이션 안 붙이면 오류가 발생한다.
        return postService.createPost(postCreateRequest)
    }
}