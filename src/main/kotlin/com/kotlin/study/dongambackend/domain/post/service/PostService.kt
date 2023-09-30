package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.mapper.PostMapper
import com.kotlin.study.dongambackend.domain.post.repository.PostLikeRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostQueryDslRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException


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
        return postRepository.findById(postId).orElseThrow()
    }

    fun createPost(postCreateRequest: PostCreateRequest, userId: Long): Long? {
        val post = postMapper.convertCreatePostReqDtoToEntity(userId, postCreateRequest)
        return postRepository.save(post).id
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest, postId: Long) {
        val post = postRepository.findById(postId).orElseThrow()
        post.updatePost(postUpdateRequest)
        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        if (isExistedPost(postId)) {
            postRepository.deleteById(postId)
        }
    }

    fun clickPostLike(postId: Long, userId: Long) {
        // TODO: 좋아요 고민..
    }

    fun isExistedPost(postId: Long): Boolean {
        return postRepository.findById(postId).isPresent
    }
}