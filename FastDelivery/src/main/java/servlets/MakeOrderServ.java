package Servlets;
import Services.CityService;
import Services.MakeOrderService;
import model.CityModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.Map;


@WebServlet("/MakeOrder")
public class MakeOrderServ extends HttpServlet {
    CityService cityService;
    MakeOrderService makeOrderService;

    @Override
    public void init() throws ServletException {
        cityService = new CityService();
        makeOrderService = new MakeOrderService();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CityModel> cities = cityService.getCities();
        req.setAttribute("cities", cities);

        req.getRequestDispatcher("/makeOrder.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> mp = req.getParameterMap();
        String email = (String) req.getSession().getAttribute("userName");
        int resId = makeOrderService.makeOrder(mp, email);
        if(resId < 0) {
            resp.getWriter().write("Please singup or singin");
            return;
        }
        req.setAttribute("id", resId);
        req.getRequestDispatcher("/makeOrder/makeOrderSuccess.jsp").forward(req, resp);
    }
}
