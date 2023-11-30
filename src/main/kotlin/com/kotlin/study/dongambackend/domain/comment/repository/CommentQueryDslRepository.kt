package com.kotlin.study.dongambackend.domain.comment.repository

import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.QComment
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository

@Repository
class CommentQueryDslRepository(val queryDslFactory: JPAQueryFactory) {
    val qComment = QComment.comment;

    fun searchCommentsBySlice(lastCommentId: Long, pageable: Pageable): Slice<Comment> {
        val results: List<Comment> = queryDslFactory.selectFrom(qComment)
            .where(
                qComment.isDeleted.eq(false),
                // no-offset
                qComment.id.gt(lastCommentId)
            )
            .orderBy(qComment.id.asc())
            .limit((pageable.pageSize + 1).toLong())
            .fetch() as List<Comment>

        return checkLastPage(pageable, results)
    }


    // 무한 스크롤
    private fun checkLastPage(pageable: Pageable, results: List<Comment>): Slice<Comment> {
        val hasNext = results.size > pageable.pageSize
        val mutableResults = if (hasNext) results.subList(0, pageable.pageSize) else results.toMutableList()

        return SliceImpl(mutableResults, pageable, hasNext)
    }


}