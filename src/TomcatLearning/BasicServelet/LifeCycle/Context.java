package TomcatLearning.BasicServelet.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */


import org.apache.catalina.LifecycleException;

public class Context implements Lifecycle {

    LifecycleSupport lifecycleSupport = null;
    Page[] pages = new Page[0];

    public Context() {
        this.lifecycleSupport = new LifecycleSupport(this);
    }

    public void addPages(Page[] addedpages) {
        int n = addedpages.length;
        int i = pages.length;
        Page[] res = new Page[i + n];
        int m = 0;
        for (int k = 0; k < i; k++)
            res[m++] = pages[k];
        int j = 0;
        while (j < n)
            res[m + j] = addedpages[j++];
        pages = res;
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
        System.out.println("start context");
        for (Page page : pages) {
            page.start();
        }
        lifecycleSupport.fireLifecycleEvent(Lifecycle.START_EVENT, null);
    }


    @Override
    public void stop() throws LifecycleException {
        System.out.println("stop context");
        for (Page page : pages) {
            page.stop();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.STOP_EVENT, null);
    }

}
