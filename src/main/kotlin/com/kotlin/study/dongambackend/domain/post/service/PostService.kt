package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory
import com.kotlin.study.dongambackend.domain.post.entity.id.PostLikeId
import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.post.entity.PostLike
import com.kotlin.study.dongambackend.domain.post.mapper.PostMapper
import com.kotlin.study.dongambackend.domain.post.repository.PostLikeRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostQueryDslRepository
import com.kotlin.study.dongambackend.domain.post.repository.PostRepository
import com.kotlin.study.dongambackend.domain.user.repository.UserRepository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional;

@Service
class PostService(
    private val postRepository: PostRepository,
    private val postQueryDslRepository: PostQueryDslRepository,
    private val postLikeRepository: PostLikeRepository,
    private val postMapper: PostMapper,
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getAllPost(pageable: Pageable, category: BoardCategory): GetAllPostByCategoryResponse {
        val result = postQueryDslRepository.findAllPost(pageable, category)
        val postCount = postRepository.findCountByCategory(category.toString())
        return postMapper.toGetAllPostResponse(result, postCount)
    }

    @Transactional(readOnly = true)
    fun getPostById(postId: Long): Post? {
        return postRepository.findByIdOrNull(postId)
            ?: throw NoSuchElementException()
    }

    fun createPost(postCreateRequest: PostCreateRequest, userId: Long): Long? {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException()
        val post = postMapper.convertCreatePostReqDtoToEntity(user, postCreateRequest)
        return postRepository.save(post).id
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest, postId: Long) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw NoSuchElementException()
        post.updatePost(postUpdateRequest)
        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        if (isExistedPost(postId)) {
            postRepository.deleteById(postId)
        }
    }

    fun clickPostLike(postId: Long, userId: Long) {
        if (isExistedPost(postId)) {
            val postLike = postLikeRepository.findById(userId, postId)
                ?: PostLike(PostLikeId(userId, postId))
            postLike.updatePostLike()
            postLikeRepository.save(postLike)
        }
    }

    private fun isExistedPost(postId: Long): Boolean {
        return postRepository.findById(postId).isPresent
    }
}