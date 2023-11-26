package com.kotlin.study.dongambackend.domain.post.repository

import com.kotlin.study.dongambackend.domain.post.entity.Post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long>