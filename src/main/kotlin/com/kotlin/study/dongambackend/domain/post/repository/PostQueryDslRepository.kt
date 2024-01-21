package com.kotlin.study.dongambackend.domain.post.repository

import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory
import com.kotlin.study.dongambackend.domain.post.dto.response.FindAllPostByCategory
import com.kotlin.study.dongambackend.domain.post.dto.response.QFindAllPostByCategory
import com.kotlin.study.dongambackend.domain.post.dto.response.QUserInformation
import com.kotlin.study.dongambackend.domain.post.entity.QPost

import com.querydsl.jpa.impl.JPAQueryFactory

import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
class PostQueryDslRepository(val queryDslFactory: JPAQueryFactory) {

    val qPost = QPost.post;

    fun findAllPost(pageable: Pageable, category: BoardCategory): List<FindAllPostByCategory> {
        return queryDslFactory
            .select(
                QFindAllPostByCategory(
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