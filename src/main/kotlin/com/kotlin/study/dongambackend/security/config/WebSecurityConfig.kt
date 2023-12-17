package com.kotlin.study.dongambackend.security.config

import com.kotlin.study.dongambackend.domain.user.entity.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun configure(httpSecurity: HttpSecurity): DefaultSecurityFilterChain = httpSecurity
        .csrf().disable()
        .httpBasic().disable()
        .formLogin().disable()
        .authorizeHttpRequests {
//            it.antMatchers("/api/user/", "/api/user/sign-in").permitAll()
//                .antMatchers("/api/admin/**").hasRole(Role.ADMIN.toString())
//                .antMatchers("/api/**").hasRole(Role.USER.toString())
            it.antMatchers("/api/**").permitAll()
        }
        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .build()
}