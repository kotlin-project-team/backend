package com.kotlin.study.dongambackend.security.filter

import com.kotlin.study.dongambackend.security.util.SecurityUtil
import com.kotlin.study.dongambackend.security.util.TokenProvider
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component

import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Order(0)
@Component
class AuthenticationFilter(private val tokenProvider: TokenProvider, private val securityUtil: SecurityUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = securityUtil.getBearerToken(request)

        if (accessToken != null) {
            val user = securityUtil.getUserFromToken(accessToken)

            UsernamePasswordAuthenticationToken(user, accessToken)
                .apply { details = WebAuthenticationDetails(request) }
                .also { SecurityContextHolder.getContext().authentication = it }
        }

        filterChain.doFilter(request, response)
    }
}