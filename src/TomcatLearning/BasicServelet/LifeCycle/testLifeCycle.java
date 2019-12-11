package TomcatLearning.BasicServelet.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */

import org.apache.catalina.LifecycleException;

public class testLifeCycle {
    public static void main(String[] args) throws LifecycleException {
        Context context =new Context();
        Page[] pages =new Page[10];
        for(int i=0;i<10;i++)
            pages[i] =new Page(i+1);

        context.addPages(pages);
        SimpleLifecycleListener simpleLifecycleListener =new SimpleLifecycleListener();
        // add the listener to the lifecycle object ,and the assigned event happened then it will catch it
        context.addLifecycleListener(simpleLifecycleListener);

        context.start();
        context.stop();
    }
}
