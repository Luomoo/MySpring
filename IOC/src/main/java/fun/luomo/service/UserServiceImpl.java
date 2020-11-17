package fun.luomo.service;


import fun.luomo.anno.LService;
import fun.luomo.dao.UserDao;

/**
 * @author fun.luomo
 * @date 2020/11/17 15:18
 */
@LService("service")
public class UserServiceImpl implements UserService {

    UserDao dao;

//    public UserServiceImpl(UserDao dao) {
//        this.dao = dao;
//    }

    @Override
    public void find() {
        System.out.println("service");
        dao.query();
    }

//    public void setDao(UserDao dao) {
//        this.dao = dao;
//    }
}
