package com.kotlin.study.dongambackend.security.filter

import com.kotlin.study.dongambackend.domain.user.service.UserService
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
class AuthenticationFilter(private val tokenProvider: TokenProvider, private val userService: UserService) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val accessToken = tokenProvider.getBearerToken(request)

            if (accessToken != null) {
                val claims = tokenProvider.getUserIdFromToken(accessToken)
                val userId = (claims["userId"] as Number).toLong()
                val userDetails = userService.loadUserById(userId)

                UsernamePasswordAuthenticationToken(
                    userDetails, accessToken,
                    null
                )
                    .apply { details = WebAuthenticationDetails(request) }
                    .also { SecurityContextHolder.getContext().authentication = it }
            }
        } catch (e: Exception) {
            request.setAttribute("exception", e)
        }

        filterChain.doFilter(request, response)
    }
}