package Filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CustomeFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

//      cho phep di vao link ma ng dung request
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (!req.getServletPath().startsWith("/login")) {
            if (req.getSession().getAttribute("email") != null) {
                chain.doFilter(request,response);
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
