package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.post.validator.type.BoardCategory
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import com.kotlin.study.dongambackend.domain.user.entity.User

import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

import javax.validation.constraints.NotNull
import javax.persistence.*

@DynamicInsert
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Entity
class Post(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userId: User,

    @NotNull
    var title: String,

    var content: String,

    @NotNull
    @Enumerated(EnumType.STRING)
    val category: BoardCategory,

    @ColumnDefault("0")
    val likes: Int = 0,

    @ColumnDefault("0")
    val views: Int = 0,

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    val isDeleted: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null
) : BaseTimeEntity() {

    fun updatePost(postUpdateRequest: PostUpdateRequest) {
        title = postUpdateRequest.title ?: ""
        content = postUpdateRequest.content ?: ""
    }
}