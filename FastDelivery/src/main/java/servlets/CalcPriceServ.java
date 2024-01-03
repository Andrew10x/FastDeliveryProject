package Servlets;


import Services.CalcPriceService;
import Services.CityService;
import model.CityModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.Map;

@WebServlet("/")
public class CalcPriceServ extends HttpServlet {
    CalcPriceService calcPriceService;
    CityService cityService;

    @Override
    public void init() throws ServletException {
        calcPriceService = new CalcPriceService();
        cityService = new CityService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CityModel> cities = cityService.getCities();
        req.setAttribute("cities", cities);

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        List<Float> res = calcPriceService.calcPrice(mp);

        req.setAttribute("price", (int) (float) res.get(0));
        req.setAttribute("distance", res.get(1));
        req.setAttribute("weight", res.get(2));
        req.setAttribute("evLength", (int) (float) (res.get(3)));
        req.setAttribute("pCost", (int) (float) (res.get(4)));
        req.getRequestDispatcher("/showCalcPrice.jsp").forward(req, resp);
    }
}
