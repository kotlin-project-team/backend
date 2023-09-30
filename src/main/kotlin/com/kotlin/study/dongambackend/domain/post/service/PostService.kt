package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.dto.entitykey.PostLikeKey
import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.entity.PostLike
import com.kotlin.study.dongambackend.domain.post.mapper.PostMapper
import com.kotlin.study.dongambackend.domain.post.repository.PostLikeRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostQueryDslRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    val postRepository: PostRepository,
    val postQueryDslRepository: PostQueryDslRepository,
    val postLikeRepository: PostLikeRepository,
    val postMapper: PostMapper
) {

    fun getAllPost(pageable: Pageable): List<Post> {
        return postQueryDslRepository.findAllPost(pageable)
    }

    fun getPostById(postId: Long): Post {
        return postRepository.findById(postId).get()
    }

    fun createPost(postCreateRequest: PostCreateRequest, userId: Long): Long? {
        val post = postMapper.convertCreatePostReqDtoToEntity(userId, postCreateRequest)
        return postRepository.save(post).id
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest, postId: Long) {
        val post = postRepository.findById(postId)

        if (isExisted(post)) {
            post.get().updatePost(postUpdateRequest)
            postRepository.save(post.get())
        }
    }

    fun deletePost(postId: Long) {
        val post = postRepository.findById(postId)
        if (isExisted(post)) {
            postRepository.deleteById(postId)
        }
    }

    fun clickPostLike(postId: Long, userId: Long) {
        val post = postRepository.findById(postId)
        val postLike = postLikeRepository.findById(userId, postId)

        if (isExisted(post)) {
            if (isExisted(postLike)) {
                postLike.get().updatePostLike()
                postLikeRepository.save(postLike.get())
            } else {
                postLikeRepository.save(PostLike(PostLikeKey(userId, postId)))
            }
        }
    }

    private fun <T> isExisted(findResult: Optional<T>): Boolean {
        if (findResult.isPresent) {
            return true
        }

        return false
    }
}