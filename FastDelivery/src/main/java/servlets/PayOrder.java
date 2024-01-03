package Servlets;

import Services.PayOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/PayOrder")
public class PayOrder extends HttpServlet {
    PayOrderService payOrderService;

    @Override
    public void init() throws ServletException {
        payOrderService = new PayOrderService();
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> mp = req.getParameterMap();
        req.setAttribute("orderId", mp.get("orderId")[0]);
        req.setAttribute("success", false);
        if (mp.get("year") != null) {
            if (payOrderService.payOrder(mp)) {
                req.setAttribute("success", true);
            }
        }
        req.getRequestDispatcher("/payForm.jsp").forward(req, resp);
    }
}
