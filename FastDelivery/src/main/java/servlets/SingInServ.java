package Servlets;

import Services.AuthService;
import model.UserModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/SingInServ")
public class SingInServ extends HttpServlet {
    AuthService authService;

    @Override
    public void init() {
        authService = new AuthService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/singinError.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        String login = mp.get("login")[0];
        String password = mp.get("password")[0];

        UserModel um = authService.singIn(login, password);
        if(um != null){
            HttpSession session = req.getSession();
            session.setAttribute("userRole", um.getRoleName());
            session.setAttribute("userName", um.getEmail());
            resp.sendRedirect(req.getContextPath() + "/");
        }
        else {
            doGet(req, resp);
        }
    }
}
