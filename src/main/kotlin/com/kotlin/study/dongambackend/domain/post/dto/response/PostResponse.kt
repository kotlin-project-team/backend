package com.kotlin.study.dongambackend.domain.post.dto.response

data class PostResponse(
    var id: Long,
    var userId: Long,   // TODO: Nested Object로 작성자 정보 전달하기
    var title: String,
    var content: String,
    var category: String,
    var likes: Int,
    var views: Int
) {

}