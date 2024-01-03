package Servlets;


import Services.AuthService;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SingUpServ")
public class SingUpServ extends HttpServlet {
    AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/singupError.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = authService.singUp(req.getParameterMap());
        if(userModel != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userRole", userModel.getRoleName());
            session.setAttribute("userName", userModel.getEmail());

            resp.sendRedirect(req.getContextPath() + "/");
        }
        else {
            doGet(req, resp);
        }


    }
}