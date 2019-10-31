package Hook;/*
 * @author:
 * @date:  2019/10/31/031
 * @description:
 */
public class TestHook {

    public static void main(String[] args) {

        HelloTask helloTask=new HelloTask() ;
        Component  component=new Component(helloTask) ;
        Listenner before=new Listenner("fzy") ;
        Listenner after=new Listenner("fzy") ;
        component.run();

        System.out.println(" hook before ");
        //hook
        component.hookBefore(before);
        // get hooked
        component.run();

        System.out.println(" hook after ");
        component.hookafter(after);
        component.run();

    }
}
