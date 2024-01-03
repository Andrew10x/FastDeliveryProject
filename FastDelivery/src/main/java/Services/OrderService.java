package Services;

import DAO.*;
import DB.DBSingleton;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderService {
    public OrderJoinedModel getOrder(int id) {
        OrderJoinedModel ojm;
        try {
            Connection con  = DBSingleton.getInstance().getConnection();
            OrderJoinedDAO ojd = new OrderJoinedDAO(con);
            ojm = ojd.getById(id);
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ojm;
    }


    public boolean changeStatus(Map<String, String[]> mp) {
        int res = 0;
        try {
            OrderDAO od = new OrderDAO(DBSingleton.getInstance().getConnection());
            OrderModel om = new OrderModel();
            om.setId(Integer.parseInt(mp.get("orderId")[0]));
            om.setStatusId(Integer.parseInt(mp.get("statusId")[0]));
            res = od.update(om);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res != 0;
    }
}
