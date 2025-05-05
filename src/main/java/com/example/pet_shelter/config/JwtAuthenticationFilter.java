package com.example.pet_shelter.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;


    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/auth/",
            "/api/users/register",
            "/v3/api-docs",
            "/swagger-ui/"
    );

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String requestUri = request.getRequestURI();


        if (isPublicEndpoint(requestUri, request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendError(response, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtConfig.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtConfig.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("Authenticated user: {}", userEmail);
                } else {
                    sendError(response, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception e) {
            log.error("JWT processing error", e);
            sendError(response, "JWT processing error", HttpStatus.UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String uri, String method) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(uri::startsWith) ||
                (uri.equals("/api/users/register") && "POST".equalsIgnoreCase(method));
    }

    private void sendError(HttpServletResponse response, String message, HttpStatus status)
            throws IOException {
        log.warn("Authentication error: {}", message);
        response.setStatus(status.value());
        response.getWriter().write(message);
        response.setContentType("application/json");
    }
}