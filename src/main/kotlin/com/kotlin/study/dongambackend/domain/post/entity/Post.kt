package com.kotlin.study.dongambackend.domain.post.entity

import com.kotlin.study.dongambackend.common.entity.BaseTimeEntity
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import javax.validation.constraints.NotNull
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "post")
@Entity
open class Post : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "user_id", nullable = false)
    var userId: Long? = null

    @NotNull
    var title: String? = null

    var content: String? = null

    var category: String? = null

    @ColumnDefault("0")
    var likes: Int? = 0

    @ColumnDefault("0")
    var views: Int? = 0
}