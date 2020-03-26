package TomcatLearning.objectConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class Processor implements Runnable {

    public String msg;
    public static boolean available = false;

    public synchronized void assign(String msg) throws InterruptedException {
        while (!available) {
            wait();
        }
        available = true;
        msg = new String(msg);
    }

    @Override
    public void run() {
        try {
            assign("helloworld ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
