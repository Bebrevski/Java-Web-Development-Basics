package sbojgb.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/view/home.xhtml",
        "/faces/view/home.xhtml",
        "/view/job-details.xhtml",
        "/view/job-delete.xhtml",
        "/view/add-job.xhtml"})
public class GuestUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String username = (String) httpServletRequest.getSession().getAttribute("username");

        if (username == null) {
            httpServletResponse.sendRedirect("login.xhtml");
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
