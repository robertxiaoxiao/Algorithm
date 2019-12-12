package TomcatLearning.LifeCycle;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PageListener implements LifecycleListener {
    int pagenum;

    public PageListener(int pagenum) {
        this.pagenum = pagenum;
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        Lifecycle lifecycle = event.getLifecycle();
       // System.out.println("Page'event\r\n" + event.getType().toString());
        DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE");
        // listener can do its process at the state it listened
        if (Lifecycle.START_EVENT.equals(event.getType())) {
            Date date = new Date();
            System.out.printf("start reading page  %d at :  %s\r\n", pagenum, df3.format(date));
        } else if (Lifecycle.STOP_EVENT.equals(event.getType())) {
            Date date = new Date();
            System.out.printf("stop reading page  %d at :  %s\r\n", pagenum, df3.format(date));
        }
    }
}
