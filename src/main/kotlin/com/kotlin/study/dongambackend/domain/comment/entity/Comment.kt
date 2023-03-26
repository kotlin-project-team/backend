package com.kotlin.study.dongambackend.domain.comment.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "device_tokeㄴn", nullable = false)
    private var deviceToken: String? = null

    @Column(name = "content", nullable = false)
    private var content: String? = null
}