package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.post.dto.entitykey.PostLikeKey
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class PostLike(

    @EmbeddedId
    val id: PostLikeKey,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean? = false
) : BaseTimeEntity() {

    fun updatePostLike() {
        this.isDeleted = this.isDeleted?.not() ?: false
    }
}