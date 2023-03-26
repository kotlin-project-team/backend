package com.kotlin.study.dongambackend.domain.post.mapper

import com.kotlin.study.dongambackend.domain.post.dto.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun toCreatePost(postCreateRequest: PostCreateRequest): Post {
        return Post(postCreateRequest.deviceToken, postCreateRequest.title, postCreateRequest.content)
    }
}