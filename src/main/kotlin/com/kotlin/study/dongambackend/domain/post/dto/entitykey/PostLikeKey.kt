package com.kotlin.study.dongambackend.domain.post.dto.entitykey

import lombok.Getter
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
@Getter
class PostLikeKey : Serializable {
    var userId: Long
    var postId: Long

    constructor(_userId: Long, _postId: Long) {
        userId = _userId
        postId = _postId
    }
}