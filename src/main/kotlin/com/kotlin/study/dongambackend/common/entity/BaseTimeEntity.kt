package com.kotlin.study.dongambackend.common.entity

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)