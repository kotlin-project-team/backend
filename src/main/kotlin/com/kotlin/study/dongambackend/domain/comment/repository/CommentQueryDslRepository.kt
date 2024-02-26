package com.kotlin.study.dongambackend.domain.comment.repository

import com.kotlin.study.dongambackend.domain.comment.dto.response.FindAllCommentResponse
import com.kotlin.study.dongambackend.domain.comment.dto.response.QFindAllCommentResponse
import com.kotlin.study.dongambackend.domain.comment.entity.QComment
import com.kotlin.study.dongambackend.domain.post.dto.response.QUserInformation
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository

@Repository
class CommentQueryDslRepository(val queryDslFactory: JPAQueryFactory) {
    val qComment = QComment.comment;

    fun searchCommentsByPostId(postId: Long, lastCommentId: Long, pageable: Pageable): List<FindAllCommentResponse> {
        // 기본 쿼리 생성
        val query = queryDslFactory
            .select(
                QFindAllCommentResponse(
                    qComment.id,
                    QUserInformation(
                        qComment.userId.id,
                        qComment.userId.studentId,
                        qComment.userId.nickname
                    ),
                    qComment.postId.id,
                    qComment.content
                )
            )
            .from(qComment)
            .where(
                qComment.isDeleted.eq(false),
                qComment.postId.id.eq(postId)
            )

        // lastCommentId가 0보다 클 경우, 해당 조건을 추가
        if (lastCommentId > 0) {
            query.where(qComment.id.lt(lastCommentId))
        }

        // 최신 댓글부터 조회하고, 페이지 사이즈에 맞게 제한
        return query.orderBy(qComment.id.desc())
            .limit(pageable.pageSize.toLong())
            .fetch()
    }
}