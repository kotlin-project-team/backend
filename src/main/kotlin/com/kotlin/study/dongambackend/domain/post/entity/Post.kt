package com.kotlin.study.dongambackend.domain.post.entity

import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import javax.validation.constraints.NotNull
import javax.persistence.*

@NoArgsConstructor
@DynamicInsert
@Table(name = "post")
@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: UInt,

    @Column(name = "user_id", nullable = false)
    var userId: UInt,

    @NotNull
    var title: String,

    var content: String,
    @NotNull
    var category: String,

    @ColumnDefault("0")
    var likes: Int? = 0,

    @ColumnDefault("0")
    var views: Int? = 0,
) {

}