package Servlets;
import Services.AllOrdersService;
import model.OrderJoinedModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {
    AllOrdersService ordersService;

    @Override
    public void init() throws ServletException {
        ordersService = new AllOrdersService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("userName");
        List<OrderJoinedModel> data = ordersService.getUserOrders(email);

        req.setAttribute("data", data);
        getServletContext().getRequestDispatcher("/myOrders.jsp").forward(req, resp);
    }
}
