package Services;

import DAO.RoleDAO;
import DAO.UserDAO;
import DB.DBSingleton;
import HelperClasses.Encryptor;
import model.RoleModel;
import model.UserModel;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class AuthService {
    public UserModel singIn(String login, String password) {
        UserDAO ud;
        UserModel um;

        try {
            ud = new UserDAO(DBSingleton.getInstance().getConnection());
            um = ud.get(login);
            DBSingleton.getInstance().getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(um.getPasswordUsr() == null) {
            return null;
        }

        Encryptor enc = new Encryptor();
        if(enc.checkPassword(password, um.getPasswordUsr()))
            return um;
        return null;
    }

    public UserModel singUp(Map<String, String[]> mp) {
        UserModel userModel;
        try {
            Connection con = DBSingleton.getInstance().getConnection();
            UserDAO ud = new UserDAO(con);
            if(ud.get(mp.get("login")[0]).getPasswordUsr()  != null){
                DBSingleton.getInstance().getConnection().close();
                return null;
            }

            Encryptor enc = new Encryptor();

            String hashPassword = enc.encryptPassword(mp.get("password")[0]);
            userModel = new UserModel();
            userModel.setUserName(mp.get("pib")[0]);
            userModel.setPhone(mp.get("phoneNumber")[0]);
            userModel.setEmail(mp.get("login")[0]);
            userModel.setPasswordUsr(hashPassword);

            RoleDAO rd;
            rd = new RoleDAO(con);

            RoleModel rm = rd.get("Registered user");
            userModel.setRoleName(rm.getRoleName());
            userModel.setRoleId(rm.getRoleId());


            int res;
            res = ud.add(userModel);
            DBSingleton.getInstance().getConnection().close();

            if(res == 0){
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return userModel;
    }
}
