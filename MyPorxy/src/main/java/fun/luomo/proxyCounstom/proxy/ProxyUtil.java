package fun.luomo.proxyCounstom.proxy;

import fun.luomo.proxyCounstom.dao.UserDao;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author luomo
 * @date 2020/11/16 19:37
 */
public class ProxyUtil {
    public static Object newInstance(Object target) {
        Object obj = null;
        Class<?> targetInf = target.getClass().getInterfaces()[0];
        Method[] methods = targetInf.getDeclaredMethods();
        String line = "\n";
        String tab = "\t";
        String infName = targetInf.getSimpleName();
        String content = "";
        String packageContent = "package fun.luomo;" + line;
        String importContent = "import " + targetInf.getName() + ";" + line;
        String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
        String filedContent = tab + "private " + infName + " target;" + line;
        String constructorContent = tab + "public $Proxy (" + infName + " target) {" + line
                + tab + tab + "this.target = target;" + line
                + tab + "}" + line;
        String methodContent = "";
        for (Method method : methods) {
            String returnTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            for (int i = 0; i < args.length; i++) {
                String temp = args[i].getSimpleName();
                argsContent += temp + " p" + i + ",";
                paramsContent += "p" + i + ",";
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(","));
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(","));
            }

            methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {" + line
                    + tab + tab + "System.out.println(\"---log---\");" + line
                    + tab + tab + (returnTypeName.equals("void") ? "" : "return ") + "target." + methodName + "(" + paramsContent + ");" + line
                    + tab + "}" + line;
        }
        content = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";
        FileWriter fw = null;
        try {
            File file0 = new File("D:\\Temp\\CodeTemp\\fun\\luomo");
            file0.mkdirs();
            File file = new File("D:\\Temp\\CodeTemp\\fun\\luomo\\$Proxy.java");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write(content);
            fw.flush();

            //  开始编译为class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();
            //  编译结束

            URL[] urls = new URL[]{new URL("file:D:\\\\\\Temp\\CodeTemp\\")};

            URLClassLoader loader = new URLClassLoader(urls);

            Class<?> clazz = loader.loadClass("fun.luomo.$Proxy");

            Constructor constructor = clazz.getConstructor(targetInf);

            obj = constructor.newInstance(target);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return obj;
    }

    public static void main(String[] args) {
        newInstance(UserDao.class);
    }
}
