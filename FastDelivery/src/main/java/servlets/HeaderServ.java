package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/HeaderServ")
public class HeaderServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userRole  = (String) req.getSession().getAttribute("userRole");
        if(userRole == null)
            userRole = "";
        req.setAttribute("UserRole", userRole);
        getServletContext().getRequestDispatcher("/header.jsp").forward(req, resp);
    }
}
