package TomcatLearning.objectConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class Waiter implements Runnable {

    private Message msg;

    public Waiter(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String currenthread = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println("wait to get msg :" + System.currentTimeMillis());
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currenthread+" waiter thread got notified at time:"+System.currentTimeMillis());
            //process the message now
            System.out.println(currenthread+" processed: "+msg.msg);
        }
    }
}
