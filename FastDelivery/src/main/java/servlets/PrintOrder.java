package Servlets;

import Services.OrderService;
import model.OrderJoinedModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/PrintOrder")
public class PrintOrder extends HttpServlet {
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        int orderId = Integer.parseInt(mp.get("orderId")[0]);
        OrderJoinedModel ojm = orderService.getOrder(orderId);

        req.setAttribute("orderId", mp.get("orderId")[0]);
        req.setAttribute("userName", ojm.getUserName());
        req.setAttribute("price", (int) ojm.getDeliveryCost());

        req.getRequestDispatcher("/printOrder.jsp").forward(req, resp);
    }
}
