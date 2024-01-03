package Services;

import DAO.CityDAO;
import DB.DBSingleton;
import model.CityModel;

import java.sql.SQLException;
import java.util.List;

public class CityService {
    public List<CityModel> getCities() {
        List<CityModel> cities;
        try {
            CityDAO cd = new CityDAO(DBSingleton.getInstance().getConnection());
            cities = cd.getAll();
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
