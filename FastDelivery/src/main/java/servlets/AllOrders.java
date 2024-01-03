package Servlets;

import Services.AllOrdersService;
import model.AllOrdersModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;


@WebServlet("/AllOrders")
public class AllOrders extends HttpServlet {
    AllOrdersService ordersService;

    @Override
    public void init() throws ServletException {
        ordersService = new AllOrdersService();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToAllOrders(ordersService.getAllOrders(), req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        sendToAllOrders(ordersService.getAllOrdersWithFilter(mp), req, resp);
    }

    public void sendToAllOrders(AllOrdersModel allOrdersModel, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("data", allOrdersModel.getData());
        req.setAttribute("statuses", allOrdersModel.getStatuses());
        req.setAttribute("cities", allOrdersModel.getCities());
        req.getRequestDispatcher("/allOrders.jsp").forward(req, resp);
    }

}
