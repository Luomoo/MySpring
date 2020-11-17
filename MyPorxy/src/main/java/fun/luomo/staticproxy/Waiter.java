package fun.luomo.staticproxy;

/**
 * @author luomo
 * @date 2020/11/12 17:36
 */
public class Waiter implements Car {
    BaoMaCar baoMaCar;

    public Waiter(BaoMaCar baoMaCar) {
        this.baoMaCar = baoMaCar;
    }

    @Override
    public void run() {
        System.out.println("服务员拿到车钥匙");
        baoMaCar.run();
        System.out.println("服务员把钥匙还给用户");
    }
}
