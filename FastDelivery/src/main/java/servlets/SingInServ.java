package Servlets;
import DAO.UserDAO;
import DB.DBSingleton;
import HelperClasses.Encryptor;
import HelperClasses.Session;
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
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/singinError.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        String login = mp.get("login")[0];
        String password = mp.get("password")[0];

        UserDAO ud;
        try {
            ud = new UserDAO(DBSingleton.getInstance().getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        UserModel um = ud.get(login);

        if(um.getPasswordUsr() == null) {
            doGet(req, resp);
            return;
        }

        Encryptor enc = new Encryptor();
        if(enc.checkPassword(password, um.getPasswordUsr())) {
            HttpSession session = req.getSession();



            /*if(!Objects.equals(um.getRoleName(), "Manager"))
                um.setRoleName("Registered User");*/
            session.setAttribute("userRole", um.getRoleName());
            session.setAttribute("userName", um.getEmail());
            resp.sendRedirect(req.getContextPath() + "/");
        }
        else {
            doGet(req, resp);
        }

        try {
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
