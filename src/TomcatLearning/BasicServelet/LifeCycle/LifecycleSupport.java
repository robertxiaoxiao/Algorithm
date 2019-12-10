package TomcatLearning.BasicServelet.LifeCycle;
/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class LifecycleSupport {

    public LifecycleSupport(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    private Lifecycle lifecycle = null;
    private LifecycleListener[] listeners = new LifecycleListener[0];

    public void addListener(LifecycleListener listener) {

        // dynamic array Scenario :
        // we may not have the array length before  ,so we use the dynamic array ;
        synchronized (listeners) {
            LifecycleListener[] res = new LifecycleListener[listeners.length + 1];
            int i = 0;
            for (i = 0; i < listeners.length; i++)
                res[i] = listeners[i];
            res[i] = listener;
            listeners = res;
        }
    }

    public LifecycleListener[] findlisteners() {
        return listeners;
    }

    public void removeListener(LifecycleListener listener) {

        int n = -1;
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == listener)
                break;
        }
        if (n < 0)
            return;
        int j = 0;
        LifecycleListener[] res = new LifecycleListener[listeners.length - 1];
        for (int k = 0; k < listeners.length; k++) {
            if (k != n)
                res[j++] = listeners[k];
        }
        listeners = res;
    }
}