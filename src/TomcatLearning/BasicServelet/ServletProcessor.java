package TomcatLearning.BasicServelet;/*
 * @author:
 * @date:  2019/12/4/004
 * @description:
 */

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

public class ServletProcessor {
    public void process(Request request, Response response) {
        String uri = request.geturl();
        String servletname = uri.substring(uri.lastIndexOf('/' + 1));
        // class loader for RMI
        URLClassLoader loader = null;
        int[]  a=new int[5];
        Arrays.sort(a);

    }


}
