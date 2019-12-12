package TomcatLearning.objectConcurrency.ThreadConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */


public class Data {
    int msg;
    boolean valueSet = false;

    public synchronized void get() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Consume : " + msg);
        valueSet = false;
        notify();
    }

    public synchronized void set(int msg) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Produce :" + msg);
        valueSet = true;
        this.msg = msg;
        notify();
    }
}
