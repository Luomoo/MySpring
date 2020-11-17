package fun.luomo.staticproxy;

/**
 * @author luomo
 * @date 2020/11/12 17:38
 */
public class Client {
    public static void main(String[] args) {
        BaoMaCar baoMaCar = new BaoMaCar("宝马");
        Waiter waiter = new Waiter(baoMaCar);
        waiter.run();

    }
}
