package com.kotlin.study.dongambackend.domain.post.entity.id

import lombok.Getter

import java.io.Serializable

import javax.persistence.Embeddable

@Embeddable
@Getter
class PostLikeId : Serializable {

    var userId: Long
    var postId: Long

    constructor(_userId: Long, _postId: Long) {
        userId = _userId
        postId = _postId
    }
}