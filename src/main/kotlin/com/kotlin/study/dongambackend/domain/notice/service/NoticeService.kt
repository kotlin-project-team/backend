package com.kotlin.study.dongambackend.domain.notice.service

import com.kotlin.study.dongambackend.common.config.BaseException
import com.kotlin.study.dongambackend.common.config.ResponseStatus
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.entity.Notice
import com.kotlin.study.dongambackend.domain.notice.repository.NoticeRepository
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.io.IOException

@Service
@Slf4j
class NoticeService(private val noticeRepository: NoticeRepository) {

    fun createNotice(noticeCreateRequest: NoticeCreateRequest): Long? {
        val notice = Notice(noticeCreateRequest.content)
        noticeRepository.save(notice)

        return notice.id
    }

    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest, noticeId: Long): Notice {
        val notice = noticeRepository.findById(noticeId).get()
        notice.updateNotice(noticeUpdateRequest)
        noticeRepository.save(notice)

        return notice
    }

    fun deleteNotice(noticeId: Long) {
        try {
            val notice = noticeRepository.findById(noticeId).get()
            // TODO: !!가 없는 경우 처리
            noticeRepository.deleteById(notice.id!!)
        } catch (e: IOException) {
            // TODO: IOException이 아닌 로그인 여부에 따른 처리로 변경.
            throw BaseException(ResponseStatus.UNAUTHORIZED);
        }
    }
}