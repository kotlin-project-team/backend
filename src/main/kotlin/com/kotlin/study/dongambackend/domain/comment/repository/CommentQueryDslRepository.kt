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
                qComment.isDeleted.eq(false)
            )
            .orderBy(qComment.id.desc())
            .limit((pageable.pageSize + 1).toLong())
            .fetch() as List<Comment>

        // 무한 스크롤 처리
        return checkLastPage(pageable, results)
    }

    // no-offset 방식 처리하는 메서드
    private fun ltCommentId(commentId: Long): BooleanExpression? {
        return if (commentId == null) {
            null
        } else qComment.id.lt(commentId)
    }


    // 무한 스크롤 방식 처리하는 메서드
    private fun checkLastPage(pageable: Pageable, results: List<Comment>): Slice<Comment> {
        var hasNext = false
        val mutableResults = results.toMutableList() // 불변 리스트를 가변 리스트로 변환

        if (mutableResults.size > pageable.pageSize) {
            hasNext = true
            mutableResults.removeAt(pageable.pageSize)
        }
        return SliceImpl(mutableResults, pageable, hasNext)
    }


}