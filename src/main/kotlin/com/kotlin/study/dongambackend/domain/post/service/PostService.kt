package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository) {
    fun getAllPost(pageable: Pageable) {
        postRepository.findAll(pageable)
    }

    fun createPost(postCreateRequest: PostCreateRequest): Post {
        // TODO: 추후 Mapper로 수정
        val post = Post(postCreateRequest.title, postCreateRequest.content, postCreateRequest.category)
        return postRepository.save(post)
    }
}