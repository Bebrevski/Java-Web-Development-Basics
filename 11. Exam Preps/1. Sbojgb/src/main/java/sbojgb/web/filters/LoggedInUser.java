package sbojgb.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/view/login.xhtml",
        "/faces/view/index.xhtml",
        "/faces/view/register.xhtml"})
public class LoggedInUser implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username = (String) httpServletRequest.getSession().getAttribute("username");

        if (username != null) {
            httpServletResponse.sendRedirect("home.xhtml");
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
