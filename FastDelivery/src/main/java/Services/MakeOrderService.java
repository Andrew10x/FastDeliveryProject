package Services;

import DAO.*;
import DB.DBSingleton;
import model.OrderModel;
import model.RecipientModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MakeOrderService {
    public int makeOrder(Map<String, String[]> mp, String userEmail) {
        CalcPriceService calcPriceService = new CalcPriceService();
        List<Float> res = calcPriceService.calcPrice(mp);
        if(userEmail == null) {
            return -1;
        }

        UserDAO ud;
        DirectionDAO dd;
        StatusDAO sd;
        RecipientDAO rd;
        OrderDAO od;
        try {
            Connection con = DBSingleton.getInstance().getConnection();
            ud = new UserDAO(con);
            dd = new DirectionDAO(con);
            sd = new StatusDAO(con);
            rd = new RecipientDAO(con);
            od = new OrderDAO(con);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        OrderModel om = new OrderModel();
        om.setUserId(ud.get(userEmail).getUserId());
        om.setDirectionId(dd.getByTwoFields( Integer.parseInt(mp.get("cityFrom")[0]),
                Integer.parseInt(mp.get("cityTo")[0])).getDirectionId());
        om.setStatusId(sd.get("Створено").getStatusid());
        om.setWeightOrd(res.get(2));
        om.setLengthOrd(Integer.parseInt(mp.get("length")[0]));
        om.setHeightOrd(Integer.parseInt(mp.get("height")[0]));
        om.setWidthOrd(Integer.parseInt(mp.get("width")[0]));
        om.setTypeId(Integer.parseInt(mp.get("pType")[0]));
        om.setSumInsured(Float.parseFloat(mp.get("pCost")[0]));
        om.setAdress(mp.get("addressRec")[0]);
        om.setDeliveryCost((int) (float) res.get(0));

        RecipientModel rm = new RecipientModel();
        rm.setRecipientName(mp.get("pibRec")[0]);
        rm.setRecipientPhone(mp.get("telRec")[0]);
        int recId = rd.add(rm);
        om.setRecipientId(recId);

        int resId = od.add(om);

        try {
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resId;
    }
}
