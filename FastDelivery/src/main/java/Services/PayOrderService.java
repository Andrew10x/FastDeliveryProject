package Services;

import DAO.OrderDAO;
import DAO.StatusDAO;
import DB.DBSingleton;
import model.OrderModel;
import model.StatusesModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class PayOrderService {
    public boolean payOrder(Map<String, String[]> mp) {
        int res = 0;
        if(mp.get("year") != null) {
            StatusDAO sd;
            OrderDAO od;
            try {
                Connection con  = DBSingleton.getInstance().getConnection();
                sd = new StatusDAO(con);
                od = new OrderDAO(con);
                StatusesModel sm = sd.get("Оплачено");
                OrderModel om = new OrderModel();
                om.setStatusId(sm.getStatusid());
                om.setId(Integer.parseInt(mp.get("orderId")[0]));
                res = od.update(om);
                con.close();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return res != 0;
    }
}
