package com.kotlin.study.dongambackend.domain.comment.repository

import com.kotlin.study.dongambackend.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
}