package Hook;/*
 * @author:
 * @date:  2019/10/31/031
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

//  task interface
public class Component {

    List<Listenner> beforelistenners;
    List<Listenner> afterlistenners;
    task  realTask ;
    static int default_size=20 ;

    public Component(task realTask){
        this.realTask =realTask  ;
        beforelistenners =new LinkedList<>();
        afterlistenners =new LinkedList<>( );
    }

    public void  hookBefore(Listenner listenner){
        beforelistenners.add(listenner) ;
    }

    public void  hookafter(Listenner listenner){
        afterlistenners.add(listenner) ;
    }

    public void run() {
        before_run();
        realTask.run();
        after_run();
    }

    public void before_run(){
        if(!beforelistenners.isEmpty())
                 System.out.println("before world ");
        for(Listenner listenner:beforelistenners)
            listenner.exec();
    }
    public  void after_run(){
        if(!afterlistenners.isEmpty())
            System.out.println("after world ");

        for(Listenner listenner:afterlistenners)
            listenner.exec();
    }

}
