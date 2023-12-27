package com.kotlin.study.dongambackend.domain.notice.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.common.type.BoardCategoryType
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name = "notice")
@Entity
class Notice(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @NotNull
    var title: String?,

    var content: String?,

    @NotNull
    @Enumerated(EnumType.STRING)
    val category: BoardCategoryType,

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    var isDeleted: Boolean = false


) : BaseTimeEntity() {
    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest) {
        content = noticeUpdateRequest.content
        title = noticeUpdateRequest.title
    }

    fun deleteNotice() {
        isDeleted = true
    }
}