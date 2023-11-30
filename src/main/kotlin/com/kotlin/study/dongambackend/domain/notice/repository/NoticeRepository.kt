package com.kotlin.study.dongambackend.domain.notice.repository

import com.kotlin.study.dongambackend.domain.notice.entity.Notice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticeRepository : JpaRepository<Notice, Long>