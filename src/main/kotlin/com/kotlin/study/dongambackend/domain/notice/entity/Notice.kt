package com.kotlin.study.dongambackend.domain.notice.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Getter
@NoArgsConstructor
@DynamicInsert
@SQLDelete(sql = "UPDATE notice SET is_deleted = true WHERE id = ?")
@Table(name = "notice")
@Entity
class Notice(

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @NotNull
    var title: String?,

    var content: String?,

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    var isDeleted: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null
) : BaseTimeEntity() {
    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest) {
        content = noticeUpdateRequest.content
        title = noticeUpdateRequest.title
    }

    fun deleteNotice() {
        isDeleted = true
    }
}