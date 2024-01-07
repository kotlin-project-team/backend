package com.kotlin.study.dongambackend.domain.notice.mapper

import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.entity.Notice
import org.springframework.stereotype.Component

@Component
class NoticeMapper {
    fun convertCreateNoticeReqDtoToEntity(userId: Long, createRequest: NoticeCreateRequest): Notice {
        return Notice(userId, createRequest.title, createRequest.content, createRequest.category)
    }
}