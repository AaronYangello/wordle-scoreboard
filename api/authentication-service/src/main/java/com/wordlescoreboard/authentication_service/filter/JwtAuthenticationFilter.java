package com.wordlescoreboard.authentication_service.filter;

import com.wordlescoreboard.authentication_service.model.JwtTokenUtil;
import com.wordlescoreboard.authentication_service.model.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws java.io.IOException, jakarta.servlet.ServletException {

        logger.info("Processing request: {}", request.getRequestURI());
        String header = request.getHeader("Authorization");
        logger.debug("Authorization header: {}", header);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            logger.warn("Authorization header is missing or does not start with 'Bearer '");
            return;
        }

        String token = header.substring(7);
        logger.debug("Extracted token: {}", token);
        try {
            Claims claims = jwtTokenUtil.getClaimsFromToken(token);
            logger.info("Claims from token: {}", claims);
            String username = claims.getSubject();
            logger.info("Username: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Create a UserDetails object (or load user details from the database if needed)
                UserDetails userDetails = new User(username);

                // Create an Authentication object
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Set additional details
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Store the Authentication object in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("Error processing request", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
