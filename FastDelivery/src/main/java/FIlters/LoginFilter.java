package FIlters;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("LogFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String servletPath = req.getServletPath();

        System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath //
                + ", URL =" + req.getRequestURL());

        //HttpSession session = req.getSession(false);

        if(servletPath.contains("/resources") || Objects.equals(servletPath, "/auth/singin.jsp") ||
                Objects.equals(servletPath, "/auth/singup.jsp") ||
                Objects.equals(servletPath, "/SingInServ") ||
                Objects.equals(servletPath, "/SingUpServ") ||
                Objects.equals(servletPath, "/")
        ) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = req.getSession();
        String userRole = (String) session.getAttribute("userRole");
        if(userRole == null) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(req.getContextPath() + "/auth/singin.jsp");
            return;
        }
        if(servletPath.contains("/AllOrders")) {
            if(userRole.equals("Manager")) {
                chain.doFilter(request, response);
            }
            else {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
        else {
            chain.doFilter(request, response);
        }

       /* Cookie[] cookies = req.getCookies();
        String UserRole = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if(Objects.equals(c.getName(), "UserRole"))
                    UserRole = c.getValue();
            }

        }

        if(!Objects.equals(UserRole, "")) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(req.getContextPath() + "/auth/singin.jsp");
        }*/

    }

}