package com.hrp.user.microservices.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Imprimir detalles de la solicitud
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Request Headers: ");
        request.getHeaderNames().asIterator().forEachRemaining(header -> {
            System.out.println(header + ": " + request.getHeader(header));
        });

        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}