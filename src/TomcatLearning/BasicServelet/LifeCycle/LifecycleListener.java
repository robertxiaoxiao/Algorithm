package TomcatLearning.BasicServelet.LifeCycle;

import org.apache.catalina.LifecycleEvent;

public interface LifecycleListener {
    public void lifecycleEvent(LifecycleEvent event);
}
