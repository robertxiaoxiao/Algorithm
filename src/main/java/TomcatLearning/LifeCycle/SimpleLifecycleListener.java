package TomcatLearning.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */

public class SimpleLifecycleListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        Lifecycle lifecycle = event.getLifecycle();
        System.out.print("SimpleLifecycleListener'event:  " );

        // listener can do its process at the state it listened
        if (Lifecycle.START_EVENT.equals(event.getType())) {
            System.out.println("start reading context");
        } else if (Lifecycle.STOP_EVENT.equals(event.getType()))
            System.out.println("stop reading context");
    }
}
