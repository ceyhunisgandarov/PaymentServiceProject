package com.payment.service.security;

import com.payment.service.security.model.User;
import com.payment.service.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userN;
//        if (authHeader!=null && authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            jwt = authHeader.substring(7);
//            System.out.println(authHeader);
//            System.out.println(jwt);
//            userN = jwtService.extractUsername(jwt);
//            if (userN != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userN);
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        }
//        filterChain.doFilter(request, response);

        final String authorizationHeader = request.getHeader("Authorization");
        /*String uri = request.getRequestURI();
        if (uri.startsWith("/experimental/")) {
            chain.doFilter(request, response);
            return;
        }*/

        String username = null, token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
                System.out.println("JWT Token: " + token + " is created!");
            } catch (IllegalArgumentException e) {
                e.getMessage();
                System.out.println("Unable to get JWT Token: " + token + "!");
            } catch (ExpiredJwtException e) {
                e.getMessage();
                System.out.println("JWT Token: " + token + " is expired!");
            }
        } else {
            System.out.println("Token is not exist in the header!");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = repository.findUserByUsername(username);
            if (jwtService.isTokenValid(token, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                Collection<? extends GrantedAuthority> authorities = usernamePasswordAuthenticationToken.getAuthorities();

                authorities.forEach(authority -> {
                    System.out.println("Role: " + authority.getAuthority());
                });

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("JWT Token: " + token + " is validated!");
            }
        }

        filterChain.doFilter(request, response);

    }

}
