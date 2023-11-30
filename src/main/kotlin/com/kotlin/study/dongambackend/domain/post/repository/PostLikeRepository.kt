package com.kotlin.study.dongambackend.domain.post.repository

import com.kotlin.study.dongambackend.domain.post.entity.PostLike

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostLikeRepository : JpaRepository<PostLike, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM post_like WHERE user_id = :userId AND post_id = :postId"
    )
    fun findById(userId: Long, postId: Long): PostLike?
}