package fun.luomo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author luomo
 * @date 2020/11/12 17:36
 */
public class Waiter implements InvocationHandler {
    BaoMaCar baoMaCar;

    public Waiter(BaoMaCar baoMaCar) {
        this.baoMaCar = baoMaCar;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("服务员拿到钥匙");
        method.invoke(baoMaCar, args);
        System.out.println("服务员还回钥匙");
        return null;
    }
}
