package com.kotlin.study.dongambackend.domain.post.repository

import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import com.kotlin.study.dongambackend.domain.post.dto.response.PostCategoryFreeResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.QPostCategoryFreeResponse
import com.kotlin.study.dongambackend.domain.post.dto.response.QUserInformation
import com.kotlin.study.dongambackend.domain.post.entity.QPost

import com.querydsl.jpa.impl.JPAQueryFactory

import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
class PostQueryDslRepository(val queryDslFactory: JPAQueryFactory) {

    val qPost = QPost.post;

    /**
     * isDeleted = true인 게시물 제외한 전체 게시물 리스트 페이지네이션
     */
    fun findAllPost(pageable: Pageable, category: BoardCategoryType): List<PostCategoryFreeResponse> {
        return queryDslFactory
            .select(
                QPostCategoryFreeResponse(
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