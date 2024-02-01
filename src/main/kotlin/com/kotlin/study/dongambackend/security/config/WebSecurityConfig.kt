package com.kotlin.study.dongambackend.security.config

import com.kotlin.study.dongambackend.domain.user.validator.type.UserRole
import com.kotlin.study.dongambackend.security.filter.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableMethodSecurity
class WebSecurityConfig(private val authenticationFilter: AuthenticationFilter) {

    @Bean
    fun configure(httpSecurity: HttpSecurity): DefaultSecurityFilterChain = httpSecurity
        .csrf().disable()
        .httpBasic().disable()
        .formLogin().disable()
        .headers { it.frameOptions().sameOrigin() }
        .authorizeHttpRequests {
            it.antMatchers("/api/user/sign-in", "/api/user/sign-up").permitAll()
                .antMatchers("/api/**").authenticated()
//                .antMatchers("/api/admin/**").hasRole(UserRole.ADMIN.toString())
//                .antMatchers("/api/**").hasRole(UserRole.USER.toString())
//            it.antMatchers("/api/**").permitAll()
        }
        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .addFilterBefore(authenticationFilter, BasicAuthenticationFilter::class.java)
        .build()
}