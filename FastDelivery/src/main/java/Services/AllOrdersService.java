package Services;

import DAO.CityDAO;
import DAO.OrderJoinedDAO;
import DAO.StatusDAO;
import DB.DBSingleton;
import model.AllOrdersModel;
import model.CityModel;
import model.OrderJoinedModel;
import model.StatusesModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AllOrdersService {
    public AllOrdersModel getAllOrders() {
        AllOrdersModel allOrdersModel;
        try {
            Connection connection = DBSingleton.getInstance().getConnection();
            OrderJoinedDAO ojd = new OrderJoinedDAO(connection);
            StatusDAO sd = new StatusDAO(connection);
            CityDAO cd = new CityDAO(connection);
            List<OrderJoinedModel> data;
            List<StatusesModel> statuses;
            List<CityModel> cities;
            data = ojd.getAll();
            statuses = sd.getAll();
            cities = cd.getAll();
            allOrdersModel = new AllOrdersModel(data, statuses, cities);
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allOrdersModel;
    }

    public AllOrdersModel getAllOrdersWithFilter(Map<String, String[]> mp) {
        AllOrdersModel allOrdersModel;
        try {
            Connection connection = DBSingleton.getInstance().getConnection();
            StatusDAO sd = new StatusDAO(connection);
            OrderJoinedDAO ojd = new OrderJoinedDAO(connection);
            CityDAO cd = new CityDAO(connection);
            List<OrderJoinedModel> data;
            List<StatusesModel> statuses;
            List<CityModel> cities;

            int orderId = 0;
            if(!Objects.equals(mp.get("orderId")[0], "")) {
                orderId = Integer.parseInt(mp.get("orderId")[0]);
            }
            data = ojd.getAllWithFilter(orderId, mp.get("email")[0], mp.get("status")[0],
                    mp.get("date")[0], mp.get("cityFrom")[0], mp.get("cityTo")[0]);
            statuses = sd.getAll();
            cities = cd.getAll();
            allOrdersModel = new AllOrdersModel(data, statuses, cities);
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allOrdersModel;
    }

    public List<OrderJoinedModel> getUserOrders(String email) {
        List<OrderJoinedModel> data;
        if(email == null)
            return null;
        try {
            OrderJoinedDAO ojd = new OrderJoinedDAO(DBSingleton.getInstance().getConnection());
            data = ojd.getAllWithFilter(0, email, "", "", "", "");
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
