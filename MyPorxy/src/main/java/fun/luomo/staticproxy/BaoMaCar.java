package fun.luomo.staticproxy;

/**
 * @author luomo
 * @date 2020/11/12 17:35
 */
public class BaoMaCar implements Car {
    String name;

    public BaoMaCar(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "运行了！");
    }
}
