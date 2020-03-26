package TomcatLearning.BasicServelet;/*
 * @author:
 * @date:  2019/12/6/006
 * @description:
 */

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class customerClassLoader extends ClassLoader {
    private static final String WEB_ROOT = System.getProperty("user.dir") + System.getProperty("file.separator") + "WebServerRoot" + System.getProperty("file.separator");

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String cname = WEB_ROOT + name.replace('.', '/') + ".class";
            byte[] classBytes = Files.readAllBytes(Paths.get(cname));
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) {
                throw new ClassNotFoundException(name);
            }
            return cl;
        } catch (IOException e) {
            System.out.print(e);
            throw new ClassNotFoundException(name);
        }
    }

    public static void main(String[] args) {
        try {
            ClassLoader loader = new customerClassLoader();
            //调用loadClass加载sample.loader.SayHello类
            //无法加载到该类，因此会调用findClass方法
            Class c = loader.loadClass("hello");
            Object obj = c.newInstance();
            Method m = c.getDeclaredMethod("hello", null);
            m.invoke(obj, null);
        } catch (Throwable e) {
            System.out.println(e);
        }
    }


}
