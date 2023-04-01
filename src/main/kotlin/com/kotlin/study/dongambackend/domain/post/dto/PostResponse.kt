package com.kotlin.study.dongambackend.domain.post.dto

data class PostResponse(var id: Long?, var deviceToken: String?, var title: String, var content: String, var likes: Int?, var reportCounts: Int?) {

}