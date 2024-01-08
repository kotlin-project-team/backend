package com.kotlin.study.dongambackend.domain.comment.repository

import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.QComment
import com.kotlin.study.dongambackend.domain.comment.mapper.CommentMapper
import com.kotlin.study.dongambackend.domain.comment.service.CommentService
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository

@Repository
class CommentQueryDslRepository(val queryDslFactory: JPAQueryFactory) {
    val qComment = QComment.comment;

    fun searchCommentsBySlice(lastCommentId: Long, pageable: Pageable): List<Comment> {
        return queryDslFactory.selectFrom(qComment)
            .where(
                qComment.isDeleted.eq(false),
                qComment.id.gt(lastCommentId)
            )
            .orderBy(qComment.id.asc())
            .limit((pageable.pageSize + 1).toLong())
            .fetch()
    }
}