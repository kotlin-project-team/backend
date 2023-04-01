package com.kotlin.study.dongambackend.domain.post.mapper

import com.kotlin.study.dongambackend.domain.post.dto.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.PostResponse
import com.kotlin.study.dongambackend.domain.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun createPostEntity(postCreateRequest: PostCreateRequest): Post {
        return Post(
            deviceToken = postCreateRequest.deviceToken,
            title = postCreateRequest.title,
            content = postCreateRequest.content
        )
    }
    fun createPostResponse(post: Post): PostResponse {
        return PostResponse(
            id = post.id,
            deviceToken = post.deviceToken,
            title = post.title,
            content = post.content,
            likes = post.likes,
            reportCounts = post.reportCounts
        )
    }
}