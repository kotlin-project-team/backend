package com.kotlin.study.dongambackend.domain.notice.service

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.exception.common.NotFoundException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.entity.Notice
import com.kotlin.study.dongambackend.domain.notice.repository.NoticeRepository
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import com.kotlin.study.dongambackend.domain.notice.dto.response.NoticeCategoryFreeResponse
import com.kotlin.study.dongambackend.domain.notice.mapper.NoticeMapper
import com.kotlin.study.dongambackend.domain.notice.repository.NoticeQueryDslRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
class NoticeService(
    private val noticeRepository: NoticeRepository,
    private val noticeQueryDslRepository: NoticeQueryDslRepository,
    private val noticeMapper: NoticeMapper
) {

    fun createNotice(noticeCreateRequest: NoticeCreateRequest, userId: Long): Long? {
        val notice = noticeMapper.convertCreateNoticeReqDtoToEntity(userId, noticeCreateRequest)
        return noticeRepository.save(notice).id
    }

    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest, noticeId: Long): Notice {
        val notice = noticeRepository.findById(noticeId).get()
        notice.updateNotice(noticeUpdateRequest)
        noticeRepository.save(notice)

        return notice
    }

    fun deleteNotice(noticeId: Long) {
        val notice = noticeRepository.findByIdOrNull(noticeId)
            ?: throw NotFoundException()

        // TODO: Unauthorized 및 ForbiddenToken 처리
        val commentIdToDelete = notice.id ?: throw NotFoundException()
        noticeRepository.deleteById(commentIdToDelete)
    }

    @Transactional(readOnly = true)
    fun getAllNotice(pageable: Pageable): List<NoticeCategoryFreeResponse> {
        return noticeQueryDslRepository.findAllPost(pageable)
    }
}