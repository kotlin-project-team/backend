package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.post.dto.request.PostUpdateRequest
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import javax.validation.constraints.NotNull
import javax.persistence.*

@NoArgsConstructor
@DynamicInsert
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE id = ?")
@Table(name = "post")
@Entity
class Post(
    title: String,
    content: String,
    category: String,
    userId: Long
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    // TODO: userId 참조 필요
    @Column(name = "user_id", nullable = false)
    val userId: Long = userId

    @NotNull
    var title: String = title

    var content: String = content

    val category: String = category

    @ColumnDefault("0")
    val likes: Int? = 0

    @ColumnDefault("0")
    val views: Int? = 0

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    val isDeleted: Boolean = false

    fun updatePost(postUpdateRequest: PostUpdateRequest) {
        title = postUpdateRequest.title
        content = postUpdateRequest.content
    }
}