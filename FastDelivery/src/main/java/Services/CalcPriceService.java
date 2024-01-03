package Services;

import DAO.ConstantsDAO;
import DAO.DirectionDAO;
import DB.DBSingleton;
import model.ConstantsModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalcPriceService {
    public  List<Float> calcPrice(Map<String, String[]> mp) {
        ConstantsModel distance, weight, avLength;
        int v;
        try {
            ConstantsDAO cd;
            DirectionDAO dd;
            cd = new ConstantsDAO(DBSingleton.getInstance().getConnection());
            dd = new DirectionDAO(DBSingleton.getInstance().getConnection());
            distance = cd.get("distance");
            weight = cd.get("weight");
            avLength = cd.get("avLength");
            v = dd.getByTwoFields(Integer.parseInt((mp.get("cityFrom"))[0]), Integer.parseInt((mp.get("cityTo"))[0])).getDistance();
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Вартість доставки = вц0 + к1*(в/в0-1)*вк + мц0 + к2*(м/м0-1)*мк + оц0 + к3*(о/о0-1)*ок
        int k1 = 1, k2 = 1, k3 = 1;
        float m = Float.parseFloat((mp.get("weight"))[0]);
        int length = Integer.parseInt((mp.get("length"))[0]);
        int width = Integer.parseInt((mp.get("width"))[0]);
        int height = Integer.parseInt((mp.get("height"))[0]);
        float o = ((float) length + width + height)/3;
        float pCost = Float.parseFloat((mp.get("pCost"))[0]);
        if(v <= distance.getMinValue())
            k1 = 0;
        if(m <= weight.getMinValue())
            k2 = 0;
        if(o <= avLength.getMinValue())
            k3 = 0;

        float price = (distance.getMinPrice() + k1*(v/distance.getMinValue() - 1)*distance.getCoef() +
                weight.getMinPrice() + k2*(m/weight.getMinValue() - 1)* weight.getCoef() + avLength.getMinPrice() +
                k3*(o/ avLength.getMinValue() - 1)* avLength.getCoef());

        List<Float> l = new ArrayList<>();
        l.add(price);
        l.add((float) v);
        l.add(m);
        l.add(o);
        l.add(pCost);
        return l;
    }
}
