package TomcatLearning.DesignPattern.Facade;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

public class FacadeTest {

    public static void main(String[] args) {
        Request hreq = new httprequest("http 1.0");
        // method leaking out of domain
        ((httprequest) hreq).outerPrint();
        requestFacade reqFacade = new requestFacade();
        reqFacade.setHttprequest(hreq);
        reqFacade.printRequest(new String("hello world"));
    }

}
