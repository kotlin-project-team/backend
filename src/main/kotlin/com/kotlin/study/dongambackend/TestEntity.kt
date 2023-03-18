package com.kotlin.study.dongambackend

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class TestEntity(var _name: String) {
    @Id
    @GeneratedValue
    var id: Int? = null

    @Column
    var name: String = _name
}