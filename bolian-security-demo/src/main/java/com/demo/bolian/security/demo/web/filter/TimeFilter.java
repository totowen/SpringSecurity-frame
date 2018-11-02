package com.demo.bolian.security.demo.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 过滤器是遵从javaee的规范，只获取请求响应和返回响应内容，跟Spring没有直接关系，没法获取是哪个控制器进行的操作
 */
@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("time filter finish time: "+ (new Date().getTime()-start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
