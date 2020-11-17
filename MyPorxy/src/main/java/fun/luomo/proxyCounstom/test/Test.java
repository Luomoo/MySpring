package fun.luomo.proxyCounstom.test;

import fun.luomo.proxyCounstom.dao.UserDao;
import fun.luomo.proxyCounstom.dao.UserDaoImpl;
import fun.luomo.proxyCounstom.proxy.ProxyUtil;

/**
 * @author luomo
 * @date 2020/11/16 20:38
 */
public class Test {
    public static void main(String[] args) {
        UserDao userDao = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
        String query = userDao.query("aaa", "asd");
        System.out.println(query);

    }
}
