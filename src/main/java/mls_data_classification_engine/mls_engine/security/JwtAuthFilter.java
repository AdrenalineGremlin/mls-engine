package mls_data_classification_engine.mls_engine.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;
import mls_data_classification_engine.mls_engine.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mls_data_classification_engine.mls_engine.repository.UserRepository;

// intecepts requests, verifies if token is genuine, and extracts the username
// and this registers identiy with spring security context
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    // inherated abstract method
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // get auth
        String authHeader = request.getHeader("Authorization");
        // bearer is the format label that stores token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // verifies token is valid
        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow();
        // treat as a logged in user
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()));
        filterChain.doFilter(request, response);
    }

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
}
