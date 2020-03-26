package TomcatLearning.DesignPattern.Facade;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

public class httprequest implements Request {

    public String httpcontext = null;

    public httprequest(String httpcontext) {
        this.httpcontext = httpcontext;
    }

    public void fixContext(String fixCon) {
        if (httpcontext == null || httpcontext.equals(fixCon))
            return;

        httpcontext = new String(fixCon);
    }

    public void outerPrint(){
        System.out.println("you cannot get the method using type trans ,it is a type of method leaking");
    }

    @Override
    public void printRequest(String msg) {
        System.out.printf("current http context : %s \r\n",httpcontext);
        System.out.printf("msg to print : %s \r\n" ,msg);
    }
}
