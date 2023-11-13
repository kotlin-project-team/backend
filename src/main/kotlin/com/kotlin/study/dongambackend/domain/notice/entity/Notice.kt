package com.kotlin.study.dongambackend.domain.notice.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.notice.dto.request.NoticeUpdateRequest
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import javax.persistence.*

@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name = "notice")
@Entity
class Notice : BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    var content: String? = null

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    var isDeleted: Boolean = false

    constructor(_content: String) {
        content = _content
    }

    fun updateNotice(noticeUpdateRequest: NoticeUpdateRequest) {
        content = noticeUpdateRequest.content
    }

    fun deleteNotice() {
        isDeleted = true
    }
}