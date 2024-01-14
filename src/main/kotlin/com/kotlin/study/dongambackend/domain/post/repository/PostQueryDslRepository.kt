package com.kotlin.study.dongambackend.domain.post.repository

import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import com.kotlin.study.dongambackend.domain.post.dto.response.GetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.QGetAllPostByCategoryResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.QUserInformation
import com.kotlin.study.dongambackend.domain.post.entity.QPost

import com.querydsl.jpa.impl.JPAQueryFactory

import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
class PostQueryDslRepository(val queryDslFactory: JPAQueryFactory) {

    val qPost = QPost.post;

    fun findAllPost(pageable: Pageable, category: BoardCategoryType): List<GetAllPostByCategoryResponse> {
        return queryDslFactory
            .select(
                QGetAllPostByCategoryResponse(
                    qPost.id,
                    QUserInformation(
                        qPost.userId.id,
                        qPost.userId.studentId,
                        qPost.userId.nickname
                    ),
                    qPost.title,
                    qPost.content,
                    qPost.likes,
                    qPost.views
                )
            ).from(qPost)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .where(qPost.isDeleted.eq(false), qPost.category.eq(category))
            .fetch()
    }
}