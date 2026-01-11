package org.example.hickingshop.configuration.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.repository.UserRepository;
import org.example.hickingshop.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private JwtTokenUtils jwtTokenUtils;
    private UserRepository userRepository;

    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserRepository userRepository) {
        super(authenticationManager);
        this.jwtTokenUtils = jwtTokenUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();

        User user = null;
        String header = request.getHeader(JwtTokenUtils.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(JwtTokenUtils.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(JwtTokenUtils.PREFIX_TOKEN.length());
        try {
            Claims claims = jwtTokenUtils.getTokenPayload(token);
            Optional<User> userOpt = userRepository.findByUsername(claims.getSubject());
            if (userOpt.isPresent()) {
                user = userOpt.get();
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getRoleAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
