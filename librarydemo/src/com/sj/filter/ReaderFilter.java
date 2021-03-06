package com.sj.filter;

import com.sj.entity.Reader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        HttpSession session=httpServletRequest.getSession();
        Reader reader=(Reader) session.getAttribute("reader");
        if(reader!=null)
        {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else {
            httpServletResponse.sendRedirect("/login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
