package com.kotlin.study.dongambackend.domain.comment.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import javax.persistence.*

@Getter
@DynamicInsert
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE id = ?")
@Table(name = "comment")
@Entity
class Comment : BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    // TODO: userId 참조 필요
    @Column(name = "user_id", nullable = false)
    var userId: Long = 1

    // TODO: postId 참조 필요
    @Column(name = "post_id", nullable = false)
    var postId: Long = 1

    var content: String? = null

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    var isDeleted: Boolean = false

    constructor(_content: String) {
        content = _content
    }

    fun updateComment(commentUpdateRequest: CommentUpdateRequest) {
        content = commentUpdateRequest.content
    }

    fun deleteComment() {
        isDeleted = true
    }
}