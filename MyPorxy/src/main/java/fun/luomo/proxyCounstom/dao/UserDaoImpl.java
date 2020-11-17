package fun.luomo.proxyCounstom.dao;

/**
 * @author luomo
 * @date 2020/11/16 20:39
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void query() {
        System.out.println("query()");

    }

    @Override
    public void query(String s) {
        System.out.println("query---"+s);
    }

    @Override
    public String query(String s, String s2) {

        return "asdasd";
    }
}
