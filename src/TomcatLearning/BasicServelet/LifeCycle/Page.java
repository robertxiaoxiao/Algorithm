package TomcatLearning.BasicServelet.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */

import org.apache.catalina.LifecycleException;

public class Page  implements  Lifecycle {

    LifecycleSupport lifecycleSupport = null;

    int pagenum ;

    public Page(int pagenum) {
        this.lifecycleSupport = new LifecycleSupport(this);
        this.pagenum =pagenum ;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.addListener(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleSupport.findlisteners();
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.removeListener(listener);
    }

    @Override
    public void start() throws LifecycleException {
        System.out.printf("start reading  page %d\r\n" ,pagenum);
        lifecycleSupport.fireLifecycleEvent(Lifecycle.START_EVENT,null);
    }

    @Override
    public void stop() throws LifecycleException {
        System.out.printf("stop reading  page %d\r\n" ,pagenum);
        lifecycleSupport.fireLifecycleEvent(Lifecycle.STOP_EVENT,null);
    }
}
