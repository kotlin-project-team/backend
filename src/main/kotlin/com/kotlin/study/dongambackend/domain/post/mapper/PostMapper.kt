package com.kotlin.study.dongambackend.domain.post.mapper

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostMapper {

    fun convertCreatePostReqDtoToEntity(userId: Long, createRequest: PostCreateRequest): Post {
        return Post(userId, createRequest.title, createRequest.content, createRequest.category)
    }
}