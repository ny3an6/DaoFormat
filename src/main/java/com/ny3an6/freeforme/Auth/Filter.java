package com.ny3an6.freeforme.Auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/main")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpServletRequest.getSession(false);

        if (httpSession == null || httpSession.getAttribute("user") == null) {
            req.getServletContext().getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
