package com.kotlin.study.dongambackend.domain.user.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.SQLDelete
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
    uniqueConstraints = [UniqueConstraint(
        name = "user_unique_constraint",
        columnNames = ["student_id", "nickname", "device_token"]
    )]
)
@Entity
@SQLDelete(sql = "UPDATE member SET is_active = true WHERE student_id = ?")
@DynamicInsert
class User(

    @Column(name = "student_id", nullable = false)
    val studentId: String,

    @NotBlank
    val password: String,

    @NotBlank
    val nickname: String,

    @Column(name = "is_active")
    @ColumnDefault("false")
    val isActive: Boolean,

    @Column(name = "device_token")
    val deviceToken: String,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
) : BaseTimeEntity()