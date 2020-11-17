package fun.luomo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author luomo
 * @date 2020/11/12 17:38
 */
public class Client {
    public static void main(String[] args) {
        BaoMaCar baoMaCar = new BaoMaCar("宝马");
        InvocationHandler waiter = new Waiter(baoMaCar);
        Car car = (Car) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Car.class},
                waiter);
        car.run();
        List<Integer> list = new ArrayList();
        list.sort(Comparator.comparingInt(o -> o));
    }
}
