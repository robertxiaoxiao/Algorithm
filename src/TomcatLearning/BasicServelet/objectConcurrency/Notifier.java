package TomcatLearning.BasicServelet.objectConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class Notifier implements Runnable {
    public Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String tname = Thread.currentThread().getName();
        System.out.printf("thread  %s  started\r\n", tname);
        try {
            Thread.sleep(1000);
            //    msg.msg = "hello world ";
            //     System.out.printf("change msg  %s  started\r\n", msg.msg);
            synchronized (msg) {
                msg.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
