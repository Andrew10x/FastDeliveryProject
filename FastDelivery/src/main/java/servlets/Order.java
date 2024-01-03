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

@WebServlet("/Order")
public class Order extends HttpServlet {
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        int id = Integer.parseInt(mp.get("id")[0]);

        OrderJoinedModel ojm = orderService.getOrder(id);

        req.setAttribute("ojm", ojm);
        if(mp.containsKey("print")) {
            req.setAttribute("print", true);
        }
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }
}
