package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import javax.validation.constraints.NotNull
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "post")
@Entity
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    // TODO: userId 참조 필요
    @Column(name = "user_id", nullable = false)
    private var userId: Long? = 1

    @NotNull
    private var title: String?

    private var content: String?

    private var category: String?

    @ColumnDefault("0")
    private var likes: Int? = 0

    @ColumnDefault("0")
    private var views: Int? = 0

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private var isDeleted: Boolean = false

    // TODO: basetimeEntity 적용

    constructor(_title: String, _content: String, _category: String) {
        title = _title
        content = _content
        category = _category
    }

    fun updatePost(postUpdateRequest: PostUpdateRequest) {
        title = postUpdateRequest.title
        content = postUpdateRequest.content
    }

    fun deletePost() {
        isDeleted = true
    }
}