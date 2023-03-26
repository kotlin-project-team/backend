package com.kotlin.study.dongambackend.domain.post.dto

data class PostCreateRequest(var deviceToken: String, var title: String, var content: String) {

}