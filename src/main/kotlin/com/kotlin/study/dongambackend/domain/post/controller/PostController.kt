package com.kotlin.study.dongambackend.domain.post.controller

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.PostResponse
import com.kotlin.study.dongambackend.domain.post.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPost(pageable: Pageable) {
        return postService.getAllPost(pageable)
    }
}