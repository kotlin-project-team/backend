package com.kotlin.study.dongambackend.domain.user.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import com.kotlin.study.dongambackend.domain.user.validator.type.UserRole
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Table(
    name = "member",
    uniqueConstraints = [UniqueConstraint(
        name = "user_unique_constraint",
        columnNames = ["student_id", "nickname", "device_token"]
    )]
)
@Entity
@SQLDelete(sql = "UPDATE member SET is_active = true WHERE id = ?")
@Where(clause = "is_active = true")
@DynamicInsert
class User(
    @Column(name = "student_id", nullable = false, unique = true)
    val studentId: String,

    @NotBlank
    var password: String,

    @NotBlank
    @Column(unique = true)
    var nickname: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    // 추후 NoSQL로 마이그레이션
    @Column(name = "device_token", unique = true)
    val deviceToken: String,

    @Column(name = "is_active")
    @ColumnDefault("true")
    val isActive: Boolean? = true,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null
) : BaseTimeEntity() {

    fun updatePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(password)
    }

    fun updateNickname(nickname: String) {
        this.nickname = nickname
    }
}