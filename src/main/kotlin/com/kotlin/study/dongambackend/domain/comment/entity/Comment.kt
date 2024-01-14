package com.kotlin.study.dongambackend.domain.comment.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.comment.dto.request.CommentUpdateRequest
import com.kotlin.study.dongambackend.domain.post.entity.Post
import com.kotlin.study.dongambackend.domain.user.entity.User
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Getter
@DynamicInsert
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "comment")
@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val userId: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "post_id", nullable = false)
    val postId: Post,

    var content: String? = null,

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    var isDeleted: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long? = null

) : BaseTimeEntity() {
    fun updateComment(commentUpdateRequest: CommentUpdateRequest) {
        content = commentUpdateRequest.content
    }

    fun deleteComment() {
        isDeleted = true
    }
}
