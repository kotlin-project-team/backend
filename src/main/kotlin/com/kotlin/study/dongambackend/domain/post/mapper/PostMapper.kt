package com.kotlin.study.dongambackend.domain.post.mapper

import com.kotlin.study.dongambackend.domain.post.dto.request.PostCreateRequest
import com.kotlin.study.dongambackend.domain.post.dto.response.FindAllPostByCategory
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.GetPostByIdResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.UserInformation
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.user.entity.User

import org.springframework.stereotype.Component

@Component
class PostMapper {

    // TODO: null 값에 대한 예외처리
    fun toPost(user: User, createRequest: PostCreateRequest): Post {
        return Post(
            user,
            createRequest.title ?: "",
            createRequest.content ?: "",
            createRequest.category
        )
    }

    fun toGetAllPostResponse(posts: List<FindAllPostByCategory>, postCount: Int): GetAllPostByCategoryResponse {
        return GetAllPostByCategoryResponse(posts, postCount)
    }

    fun toGetPostByIdResponse(post: Post): GetPostByIdResponse? {
        val userInformation = toUser(post.userId)

        return post.id?.let {
            GetPostByIdResponse(
                it,
                userInformation,
                post.category,
                post.title,
                post.content,
                post.likes,
                post.views
            )
        }
    }

    private fun toUser(user: User?) = user?.id?.let {
        UserInformation(
                it,
                user.studentId,
                user.nickname
            )
        } ?: UserInformation(
            0L,
            "00000000",
            "탈퇴한 사용자"
        )
}