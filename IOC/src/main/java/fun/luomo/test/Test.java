package fun.luomo.test;


import fun.luomo.org.spring.util.BeanFactory;
import fun.luomo.org.spring.util.MyAnnotationConfigApplicationContext;
import fun.luomo.service.UserService;

/**
 * @author fun.luomo
 * @date 2020/11/17 15:19
 */
public class Test {
    public static void main(String[] args) {
//        BeanFactory beanFactory = new BeanFactory("spring.xml");
//        UserService service = (UserService) beanFactory.getBean("service");

        MyAnnotationConfigApplicationContext context = new MyAnnotationConfigApplicationContext();
        context.scan("fun.luomo.service");

//        service.find();
    }
}
