package TomcatLearning.BasicServelet.objectConcurrency.ThreadConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class Consumer implements Runnable {
    Data d;

    public Consumer(Data d) {
        this.d = d;
        new Thread(this, "consumer").start();
    }

    @Override
    public void run() {
        while (true)
            d.get();
    }
}
