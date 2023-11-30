package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.post.dto.entitykey.PostLikeKey

import org.hibernate.annotations.Where

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
@Where(clause = "is_deleted = false")
class PostLike(
    @EmbeddedId
    val id: PostLikeKey,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean? = null
) : BaseTimeEntity() {

    fun updatePostLike() {
        this.isDeleted = if (this.isDeleted == null) false else !this.isDeleted!!
    }
}