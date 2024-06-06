package com.example.swadeveloperprototype.service;

import com.example.swadeveloperprototype.SwaDeveloperPrototypeApplication;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestLoggingFilter implements Filter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();
        String method = req.getMethod();
        // Extracting the ID or other parameters as needed
        String id = req.getParameter("id"); // If "id" is a query parameter
        if (id == null && path.matches(".*/\\d+$")) { // If "id" is part of the path like /api/resource/123
            id = path.substring(path.lastIndexOf('/') + 1);
        }
        String params = (id != null) ? "id=" + id : "";
        String logEntry = String.format("/%s[%s] %s", path, method, params);
        logger.info(logEntry);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
