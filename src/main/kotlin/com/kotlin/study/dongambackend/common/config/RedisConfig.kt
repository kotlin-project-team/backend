package com.kotlin.study.dongambackend.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    @Value("\${spring.redis.host}") private val host: String,
    @Value("\${spring.redis.port}") private val port: Int
) {

    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(host, port)

    @Bean
    fun redisTemplate() = RedisTemplate<Any, Any>().apply {
        this.setConnectionFactory(redisConnectionFactory())

        this.keySerializer = StringRedisSerializer()
        this.hashKeySerializer = StringRedisSerializer()
        this.valueSerializer = StringRedisSerializer()
    }
}