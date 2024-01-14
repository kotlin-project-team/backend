package com.kotlin.study.dongambackend.domain.post.mapper

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.FindAllPostByCategory
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.user.entity.User

import org.springframework.stereotype.Component

@Component
class PostMapper {

    fun convertCreatePostReqDtoToEntity(user: User, createRequest: PostCreateRequest): Post {
        return Post(user, createRequest.title, createRequest.content, createRequest.category)
    }

    fun toGetAllPostResponse(posts: List<FindAllPostByCategory>): GetAllPostByCategoryResponse {
        return GetAllPostByCategoryResponse(posts, posts.size)
    }
}