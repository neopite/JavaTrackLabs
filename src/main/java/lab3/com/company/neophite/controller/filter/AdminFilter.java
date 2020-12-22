package lab3.com.company.neophite.controller.filter;

import lab3.com.company.neophite.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if (session != null) {
            if (((User) (session.getAttribute("user"))).getRoles().get(0).getName().equalsIgnoreCase("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else res.sendError(403);
        } else {
            res.sendRedirect("/jsp/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
