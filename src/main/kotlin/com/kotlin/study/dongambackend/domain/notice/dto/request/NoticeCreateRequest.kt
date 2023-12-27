package com.kotlin.study.dongambackend.domain.notice.dto.request

import com.kotlin.study.dongambackend.common.type.BoardCategoryType

data class NoticeCreateRequest (
    var title: String,
    var content: String,
    var category: BoardCategoryType
)