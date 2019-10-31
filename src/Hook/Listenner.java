package Hook;/*
 * @author:
 * @date:  2019/10/31/031
 * @description:
 */

// hook listener
public class Listenner {
    String  targetTask ;
    public Listenner(String targetTask){
        this.targetTask=targetTask ;
    }
    public void  exec(){
        System.out.println("hello :"+targetTask);
    }
}
