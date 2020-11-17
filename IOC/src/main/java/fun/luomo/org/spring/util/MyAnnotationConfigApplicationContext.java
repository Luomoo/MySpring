package fun.luomo.org.spring.util;

import fun.luomo.anno.LService;

import java.io.File;

/**
 * @author luomo
 * @date 2020/11/17 22:13
 */
public class MyAnnotationConfigApplicationContext {

    public void scan(String basePackage) {
        try {
            String rootPath = this.getClass().getResource("/").getPath();
            String basePackagePath = basePackage.replaceAll("\\.", "\\\\");


            File file = new File(rootPath + "//" + basePackagePath);
            String[] names = file.list();
            for (String name : names) {
                name = name.replaceAll(".class", "");
                Class<?> clazz = Class.forName(basePackage + "." + name);
                if (clazz.isAnnotationPresent(LService.class)) {
                    LService annotation = clazz.getAnnotation(LService.class);
                    System.out.println(annotation.value());
                    System.out.println(clazz.newInstance());
                }

            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
