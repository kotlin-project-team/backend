package com.kotlin.study.dongambackend.domain.user.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Table(
    name = "member",
    uniqueConstraints = [
        UniqueConstraint(
        name = "user_unique_constraint",
        columnNames = ["student_id", "nickname", "device_token"]
        ),
        UniqueConstraint(
            name = "student_unique_constraint",
            columnNames = ["student_id"]
        )
    ]
)
@Entity
@SQLDelete(sql = "UPDATE member SET is_active = true WHERE id = ?")
@Where(clause = "is_active = true")
@DynamicInsert
class User(
    @Column(name = "student_id", nullable = false)
    val studentId: String,

    @NotBlank
    var password: String,

    @NotBlank
    var nickname: String,

    // 추후 NoSQL로 마이그레이션
    @Column(name = "device_token")
    val deviceToken: String,

    @Column(name = "is_active")
    @ColumnDefault("true")
    val isActive: Boolean? = true,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null
) : BaseTimeEntity() {

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateNickname(nickname: String) {
        this.nickname = nickname
    }
}