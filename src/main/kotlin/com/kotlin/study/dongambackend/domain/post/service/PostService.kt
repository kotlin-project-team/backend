package com.kotlin.study.dongambackend.domain.post.service

import com.kotlin.study.dongambackend.common.exception.common.ForbiddenException
import com.kotlin.study.dongambackend.common.exception.common.NotFoundException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory
import com.kotlin.study.dongambackend.domain.post.entity.id.PostLikeId
import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.GetPostByIdResponse
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
    fun getPostById(postId: Long): GetPostByIdResponse? {
        val post = this.getPost(postId)

        return postMapper.toGetPostByIdResponse(post)
    }

    fun createPost(postCreateRequest: PostCreateRequest, userId: Long): Long? {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException(ResponseStatusType.POST_NOT_FOUND)
        val post = postMapper.toPost(user, postCreateRequest)

        return postRepository.save(post).id
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest, postId: Long, userId: Long? = 0L) {
        val post = this.getPost(postId)

        // TODO: 인터셉터 추가
        if (post.userId.id != userId) {
            throw ForbiddenException(ResponseStatusType.POST_FORBIDDEN)
        }

        post.updatePost(postUpdateRequest)
        postRepository.save(post)
    }

    fun deletePost(postId: Long, userId: Long? = 0L) {
        val post = this.getPost(postId)

        if (post.userId.id != userId) {
            throw ForbiddenException(ResponseStatusType.POST_FORBIDDEN)
        }

        postRepository.deleteById(postId)
    }

    fun clickPostLike(postId: Long, userId: Long) {
        this.getPost(postId)

        val postLike = postLikeRepository.findById(userId, postId)
            ?: PostLike(PostLikeId(userId, postId))
        postLike.updatePostLike()
        postLikeRepository.save(postLike)
    }

    private fun getPost(postId: Long) = postRepository.findByIdOrNull(postId)
        ?: throw NotFoundException(ResponseStatusType.POST_NOT_FOUND)
}