package com.kotlin.study.dongambackend.domain.notice.controller

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.service.NoticeService
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import com.kotlin.study.dongambackend.domain.notice.dto.response.GetNoticeByIdResponse
import com.kotlin.study.dongambackend.domain.notice.dto.response.NoticeCategoryFreeResponse

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.net.URI

import java.util.*

@RestController
@RequestMapping("/api/notice")
class NoticeController(private val noticeService: NoticeService) {

    @PostMapping
    fun createNotice(
        @RequestBody noticeCreateRequest: NoticeCreateRequest
    ): ResponseEntity<Unit> {
        val noticeId = noticeService.createNotice(noticeCreateRequest, 1L)
        return ResponseEntity.created(URI.create("/api/notice/${noticeId}")).build()
    }

    @PatchMapping("/{noticeId}")
    fun updateNotice(
        @RequestBody noticeUpdateRequest: NoticeUpdateRequest,
        @PathVariable noticeId: Long
    ): ResponseEntity<BaseResponse<Unit>> {
        noticeService.updateNotice(noticeUpdateRequest, noticeId)
        return BaseResponse<Unit>(ResponseStatusType.SUCCESS).convert()
    }

    @DeleteMapping("/{noticeId}")
    fun deleteNotice(@PathVariable noticeId: Long): ResponseEntity<BaseResponse<Unit>> {
        noticeService.deleteNotice(noticeId)
        return BaseResponse<Unit>(ResponseStatusType.SUCCESS).convert()
    }

    @GetMapping
    fun getAllNotice(
        pageable: Pageable
    ): ResponseEntity<BaseResponse<List<NoticeCategoryFreeResponse>>> {
        val notices = noticeService.getAllNotice(pageable)
        return BaseResponse(ResponseStatusType.SUCCESS, notices).convert()
    }

    @GetMapping("/{noticeId}")
    fun getNoticeById(
        @PathVariable noticeId: Long
    ): ResponseEntity<GetNoticeByIdResponse> {
        val notice = noticeService.getNotice(noticeId)
        return ResponseEntity.ok().body(notice)
    }
}