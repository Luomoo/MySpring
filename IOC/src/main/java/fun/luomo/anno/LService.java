package fun.luomo.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author luomo
 * @date 2020/11/17 22:21
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface LService {
    public String value();
}
