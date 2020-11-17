package fun.luomo.test;


import fun.luomo.org.spring.util.BeanFactory;
import fun.luomo.service.UserService;

/**
 * @author fun.luomo
 * @date 2020/11/17 15:19
 */
public class Test {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory("spring.xml");
        System.out.println("----------");
        UserService service = (UserService) beanFactory.getBean("service");

        service.find();
    }
}
