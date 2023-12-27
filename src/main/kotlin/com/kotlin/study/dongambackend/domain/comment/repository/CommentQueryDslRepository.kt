package com.kotlin.study.dongambackend.domain.comment.repository

import com.kotlin.study.dongambackend.domain.comment.dto.response.CommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import com.kotlin.study.dongambackend.domain.comment.entity.QComment
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository

@Repository
class CommentQueryDslRepository(val queryDslFactory: JPAQueryFactory) {
    val qComment = QComment.comment;
//
//    fun searchCommentsBySlice(lastCommentId: Long, pageable: Pageable): Slice<CommentResponse> {
//        val results: List<CommentResponse> = queryDslFactory.selectFrom(qComment)
//            .where(
//                qComment.isDeleted.eq(false),
//                // no-offset
//                qComment.id.gt(lastCommentId)
//            )
//            .orderBy(qComment.id.asc())
//            .limit((pageable.pageSize + 1).toLong())
//            .fetch() as List<CommentResponse>
//
//        return checkLastPage(pageable, results)
//    }

    fun searchCommentsBySlice(lastCommentId: Long, pageable: Pageable): Slice<CommentResponse> {
        val results: List<Comment> = queryDslFactory.selectFrom(qComment)
            .where(
                qComment.isDeleted.eq(false),
                qComment.id.gt(lastCommentId)
            )
            .orderBy(qComment.id.asc())
            .limit((pageable.pageSize + 1).toLong())
            .fetch()

        val commentResponses = results.map { comment ->
            CommentResponse(
                id = comment.id,
                userId = comment.userId,
                postId = comment.postId,
                content = comment.content
            )
        }

        return checkLastPage(pageable, commentResponses)
    }



    // 무한 스크롤
    private fun checkLastPage(pageable: Pageable, results: List<CommentResponse>): Slice<CommentResponse> {
        val hasNext = results.size > pageable.pageSize
        val mutableResults = if (hasNext) results.subList(0, pageable.pageSize) else results.toMutableList()

        return SliceImpl(mutableResults, pageable, hasNext)
    }


}