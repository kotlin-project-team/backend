package com.kotlin.study.dongambackend.domain.post.entity

import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Getter
@NoArgsConstructor
@Table(name = "post")
@Entity
class Post(deviceToken: String, title: String, content: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "device_token", nullable = false)
    private var deviceToken: String? = null

    @Column(name = "title", nullable = false)
    private var title: String? = null

    @Column(name = "content", nullable = false)
    private var content: String? = null

    @Column(name = "likes", nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private var likes: Int? = 0

    @Column(name = "report_count", nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private var reportCount: Int? = 0
}