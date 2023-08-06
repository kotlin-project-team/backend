package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository) {
    fun getAllPost(pageable: Pageable) {
        postRepository.findAll(pageable)
    }
}