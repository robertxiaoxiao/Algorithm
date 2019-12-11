package TomcatLearning.BasicServelet.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */

public class SimpleLifecycleListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        Lifecycle lifecycle = event.getLifecycle();
        System.out.println("SimpleLifecycleListener'event\r\n" + event.getType().toString());

        if (Lifecycle.START_EVENT.equals(event.getType())) {
            System.out.println("start event");
        } else if (Lifecycle.STOP_EVENT.equals(event.getType()))
            System.out.println("stop event");
    }
}
