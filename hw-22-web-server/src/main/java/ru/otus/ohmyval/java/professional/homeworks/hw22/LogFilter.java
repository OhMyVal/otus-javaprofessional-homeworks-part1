package ru.otus.ohmyval.java.professional.homeworks.hw22;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Date;

@WebFilter(value = "/add" + "/subtract" + "/multiply" + "/div")
public class LogFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        logger.info(new Date() + " - " + httpServletRequest.getServletPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
