package com.xyxy.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*处理请求和响应的编码格式*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //处理请求编码格式
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        //处理响应编码格式
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=utf-8");
        //放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
