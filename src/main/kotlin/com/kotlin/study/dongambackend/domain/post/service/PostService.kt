package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.repository.PostQueryDslRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
// TODO: 추후 Mapper로 수정
class PostService(val postRepository: PostRepository, val postQueryDslRepository: PostQueryDslRepository) {

    fun getAllPost(pageable: Pageable): List<Post> {
        return postQueryDslRepository.findAllPost(pageable)
    }

    fun getPostById(postId: Long): Post {
        return postRepository.findById(postId).get()
    }

    fun createPost(postCreateRequest: PostCreateRequest, userId: Long): Long? {
        val post = Post(userId, postCreateRequest.title, postCreateRequest.content, postCreateRequest.category)
        return postRepository.save(post).id
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest, postId: Long) {
        val post = postRepository.findById(postId).get()
        post.updatePost(postUpdateRequest)
        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        val post = postRepository.findById(postId).get()
        // TODO: 예외처리
        postRepository.deleteById(post.id!!)
    }
}