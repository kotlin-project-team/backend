package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.PostResponse
import com.kotlin.study.dongambackend.domain.post.mapper.PostMapper
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository, val postMapper: PostMapper) {
    fun createPost(postCreateRequest: PostCreateRequest): PostResponse {
        val post = postMapper.createPostEntity(postCreateRequest)
        val result = postRepository.save(post)

        return postMapper.createPostResponse(result)
    }
}