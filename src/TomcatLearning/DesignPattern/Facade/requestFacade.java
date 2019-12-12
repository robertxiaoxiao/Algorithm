package TomcatLearning.DesignPattern.Facade;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

public class requestFacade implements  Request {

    private  Request  httprequest;

    public requestFacade() {
    }

    public void setHttprequest(Request httprequest) {
        this.httprequest = httprequest;
    }

    @Override
    public void printRequest(String msg) {
        httprequest.printRequest(msg);
    }
}
