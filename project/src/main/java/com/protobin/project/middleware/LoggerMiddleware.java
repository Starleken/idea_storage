package com.protobin.project.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class LoggerMiddleware implements Filter {

    private static final String HEADER_CORRELATION_ID  = "X-Correlation-Id";
    private static final String HEADER_CORRELATION_LOG_ID  = "correlationId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        String correlationId = httpRequest.getHeader(HEADER_CORRELATION_ID);
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }
        response.setHeader(HEADER_CORRELATION_ID, correlationId);
        MDC.put(HEADER_CORRELATION_LOG_ID, correlationId);

        try {
            logStart(httpRequest, correlationId);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception error) {
            logError(httpRequest, correlationId, error);
            throw error;
        } finally {
            MDC.clear();
        }
    }

    public void logStart(HttpServletRequest request, String correlationId) {
        log.info("[START][{}] {} {}", correlationId, request.getMethod(), request.getRequestURI());
    }

    public void logError(HttpServletRequest request, String correlationId, Exception error) {
        log.error("[ERROR][{}] {} {}: {}", correlationId, request.getMethod(), request.getRequestURI(), error.getMessage());
    }
}
