package com.example.websample.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
//@Component    -> 조금 더 세부적인 설정을 위해 FilterRegistrationBean 으로 등록
public class LogFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain
    ) throws ServletException, IOException {
        log.info("Hello filter : " + Thread.currentThread());
        chain.doFilter(request, response);
        log.info("Bye filter : " + Thread.currentThread());
    }
}
