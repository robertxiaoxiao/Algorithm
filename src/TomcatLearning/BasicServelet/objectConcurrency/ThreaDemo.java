package TomcatLearning.BasicServelet.objectConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class ThreaDemo {

    public static void main(String[] args) {
        Message msg = new Message("hello fzy");
        Waiter waiter = new Waiter(msg);
        Waiter waiter1 = new Waiter(msg);
        Notifier notifier = new Notifier(msg);
        new Thread(waiter, "waiter").start();
        new Thread(waiter1, "waiter1").start();
        new Thread(notifier, "notifier").start();
        System.out.println("all threads started...");
    }


}
