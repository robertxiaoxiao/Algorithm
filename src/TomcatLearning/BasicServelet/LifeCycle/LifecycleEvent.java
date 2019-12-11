package TomcatLearning.BasicServelet.LifeCycle;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class LifecycleEvent {
    private Object data = null;
    private Lifecycle lifecycle = null;
    private String type = null;

    public LifecycleEvent(Lifecycle lifecycle, String type,Object data) {
        this.data = data;
        this.lifecycle = lifecycle;
        this.type = type;
    }

    public Object getData() {
        return this.data;
    }

    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public String getType() {
        return this.type;
    }
}
