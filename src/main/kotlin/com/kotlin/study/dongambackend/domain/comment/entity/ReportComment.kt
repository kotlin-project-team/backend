package com.kotlin.study.dongambackend.domain.comment.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import javax.persistence.*

@Table(name = "comment_report")
@Entity
class ReportComment : BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    // TODO: commentId 참조 필요
    @Column(name = "comment_id", nullable = false)
    var commentId: Long? = 1

    // TODO: userId 참조 필요
    @Column(name = "user_id", nullable = false)
    var userId: Long? = 1

    @Column(name ="is_solved", nullable = false)
    var isSolved: Boolean = false

    var reason: String? = null

    constructor(_reason: String, _isSolved: Boolean, _commentId: Long) {
        reason = _reason
        isSolved = _isSolved
        commentId = _commentId
    }

}