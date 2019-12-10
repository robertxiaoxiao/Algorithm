package TomcatLearning.BasicServelet.objectConcurrency.ThreadConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class Producer implements  Runnable {

    Data d  ;

    public Producer(Data d) {
        this.d = d;
        new Thread(this,"Producer").start();
    }

    @Override
    public void run() {
        int i=1;
        while(true){
            d.set(i++);
        }
    }
}
