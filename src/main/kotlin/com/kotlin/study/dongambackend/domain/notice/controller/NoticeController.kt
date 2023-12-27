package com.kotlin.study.dongambackend.domain.notice.controller


import com.kotlin.study.dongambackend.common.annotation.ValidateCategory
import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeCreateRequest
import com.kotlin.study.dongambackend.domain.notice.service.NoticeService
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
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
    // TODO globalException 고려
    ): ResponseEntity<BaseResponse<ResponseStatus?>> {
        return try {
            val result = noticeService.updateNotice(noticeUpdateRequest, noticeId)
            BaseResponse<ResponseStatus?>(ResponseStatusType.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatus?>(e.status, false).convert()
        }
    }

    @DeleteMapping("/{noticeId}")
    fun deleteNotice(@PathVariable noticeId: Long): ResponseEntity<BaseResponse<ResponseStatus?>> {
        return try {
            noticeService.deleteNotice(noticeId)
            BaseResponse<ResponseStatus?>(ResponseStatusType.SUCCESS).convert()
        } catch (e: BaseException) {
            BaseResponse<ResponseStatus?>(e.status, false).convert()
        }
    }

    @GetMapping
    fun getAllNotice(
        @RequestParam(value = "category", required = true)
        @ValidateCategory(enumClass = BoardCategoryType::class) category: BoardCategoryType,
                     pageable: Pageable
    ): ResponseEntity<List<NoticeCategoryFreeResponse>> {
        val notices = noticeService.getAllNotice(category, pageable)
        return ResponseEntity.ok().body(notices)
    }


}