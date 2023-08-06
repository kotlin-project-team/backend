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
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    // TODO: userId 참조 필요
    @Column(name = "user_id", nullable = false)
    var userId: Long? = 1

    @NotNull
    var title: String?

    var content: String?

    var category: String?

    @ColumnDefault("0")
    var likes: Int? = 0

    @ColumnDefault("0")
    var views: Int? = 0

    // TODO: basetimeEntity 적용

    constructor(_title: String, _content: String, _category: String) {
        title = _title
        content = _content
        category = _category
    }
}