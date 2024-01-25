package com.kotlin.study.dongambackend.domain.notice.repository

import com.kotlin.study.dongambackend.domain.notice.dto.response.NoticeCategoryFreeResponse
import com.kotlin.study.dongambackend.domain.notice.dto.response.QNoticeCategoryFreeResponse
import com.kotlin.study.dongambackend.domain.notice.entity.QNotice
import com.querydsl.jpa.impl.JPAQueryFactory

import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
class NoticeQueryDslRepository(val queryDslFactory: JPAQueryFactory) {
    val qNotice = QNotice.notice;

    fun findAllPost(pageable: Pageable): List<NoticeCategoryFreeResponse> {
        return queryDslFactory
            .select(
                QNoticeCategoryFreeResponse(
                    qNotice.id,
                    qNotice.title,
                    qNotice.content,
                )
            ).from(qNotice)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .where(qNotice.isDeleted.eq(false))
            .fetch()
    }
}