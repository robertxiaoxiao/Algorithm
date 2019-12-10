package TomcatLearning.BasicServelet.objectConcurrency.ThreadConcurrency;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

public class TestDemo {
    public static void main(String[] args) {
        Data d =new Data();
        Consumer consumer= new Consumer(d);
        Producer producer=new Producer(d);
    }
}
