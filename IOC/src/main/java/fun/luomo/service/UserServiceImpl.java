package fun.luomo.service;


import fun.luomo.dao.TestDao;

/**
 * @author fun.luomo
 * @date 2020/11/17 15:18
 */
public class UserServiceImpl implements UserService {
    TestDao dao;

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
