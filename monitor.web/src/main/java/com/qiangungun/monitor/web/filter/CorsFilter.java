package com.qiangungun.monitor.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 解决浏览器跨域问题
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String origin = request.getHeader("Origin");
        if (StringUtils.isBlank(origin)) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        } else {
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers",
            "origin, content-type, accept, x-requested-with, sid, mycustom, smuser");
        response.addHeader("Access-Control-Max-Age", "0");
        if (httpReq.getMethod().equalsIgnoreCase("OPTIONS")) {
            httpResp.setHeader("Access-Control-Allow-Headers",
                httpReq.getHeader("Access-Control-Request-Headers"));
        }
        filterChain.doFilter(request, response);
    }

}