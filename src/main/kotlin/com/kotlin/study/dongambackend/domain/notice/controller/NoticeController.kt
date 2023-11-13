package com.kotlin.study.dongambackend.domain.notice.controller

import com.kotlin.study.dongambackend.common.config.BaseException
import com.kotlin.study.dongambackend.common.config.BaseResponse
import com.kotlin.study.dongambackend.common.config.ResponseStatus
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.service.NoticeService
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/notice")
class NoticeController(private val noticeService: NoticeService) {

    @PostMapping
    fun createnotice(
        @RequestBody noticeCreateRequest: NoticeCreateRequest
    ): ResponseEntity<Unit> {
        val noticeId = noticeService.createNotice(noticeCreateRequest)
        return ResponseEntity.created(URI.create("/api/notice/${noticeId}")).build()
    }

    @PatchMapping("/{noticeId}")
    fun updatenotice(
        @RequestBody noticeUpdateRequest: NoticeUpdateRequest,
        @PathVariable noticeId: Long
    ): ResponseEntity<BaseResponse<ResponseStatus?>> {
        return try {
            val result = noticeService.updateNotice(noticeUpdateRequest, noticeId)
            BaseResponse<ResponseStatus?>(ResponseStatus.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatus?>(e.status, false).convert()
        }
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{noticeId}")
    fun deletenotice(@PathVariable noticeId: Long): ResponseEntity<BaseResponse<ResponseStatus?>> {
        return try {
            noticeService.deleteNotice(noticeId)
            BaseResponse<ResponseStatus?>(ResponseStatus.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatus?>(e.status, false).convert()
        }

    }
}