package com.kotlin.study.dongambackend.security.config

import com.kotlin.study.dongambackend.domain.user.validator.type.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun configure(httpSecurity: HttpSecurity): DefaultSecurityFilterChain = httpSecurity
        .csrf().disable()
        .httpBasic().disable()
        .formLogin().disable()
        .authorizeHttpRequests {
            it.antMatchers("/api/user/sign-in", "/api/user/sign-up").permitAll()
                .antMatchers("/api/admin/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers("/api/**").hasRole(UserRole.USER.toString())
        }
        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .build()
}