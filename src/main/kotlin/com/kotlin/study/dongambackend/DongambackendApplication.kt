package com.kotlin.study.dongambackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class DongambackendApplication

fun main(args: Array<String>) {
    runApplication<DongambackendApplication>(*args)
}
