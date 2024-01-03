package Servlets;


import Services.OrderService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();
        orderService.changeStatus(mp);
    }
}
