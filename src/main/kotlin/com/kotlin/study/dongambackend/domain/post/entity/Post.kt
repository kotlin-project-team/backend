package com.kotlin.study.dongambackend.domain.post.entity

import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*


// TODO: private + getter 사용 가능한지 조사하고 적용하기
// TODO: null 관리하기
@NoArgsConstructor
@Table(name = "post")
@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "device_token", nullable = false)
    var deviceToken: String,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "likes", nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    var likes: Int? = 0,

    @Column(name = "report_count", nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    var reportCounts: Int? = 0) {

}