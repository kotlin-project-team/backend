package com.kotlin.study.dongambackend.domain.comment.dto.request


data class CommentSliceRequest(
    val id: Long?,
    val size: Int,
    val keyword: String
) {
    constructor(id: Long?, size: Int?, keyword: String) : this(id, size ?: 10, keyword)
}