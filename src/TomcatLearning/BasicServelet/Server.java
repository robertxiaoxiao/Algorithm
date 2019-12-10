package TomcatLearning.BasicServelet;/*
 * @author:
 * @date:  2019/12/3/003
 * @description:
 */

import java.io.IOException;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*
         1   processor connector  run
         2   object reuse and  get object from pool
 */
public class Server {

    public static final String WEB_ROOT = System.getProperty("user.dir") + System.getProperty("file.separator") + "WebServerRoot";

    public static final String SHUR_DOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        Server server = new Server();
        server.await();
        //TestSystemProperty();

    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try{
                socket =serverSocket.accept() ;
                input =socket.getInputStream();
                output =socket.getOutputStream();

                // construct request object from the accepted http request
                Request  request= new Request(input) ;
                request.parse();

                Response  response =new Response(output) ;
                response.setrequest(request);
                response.sendstatisticresource();

                socket.close();
                shutdown =request.geturl().equalsIgnoreCase(SHUR_DOWN_COMMAND) ;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void TestSystemProperty() {
        System.out.println("java_vendor:" + System.getProperty("java.vendor"));
        System.out.println("java_vendor_url:"
                + System.getProperty("java.vendor.url"));
        System.out.println("java_home:" + System.getProperty("java.home"));
        System.out.println("java_class_version:"
                + System.getProperty("java.class.version"));
        System.out.println("java_class_path:"
                + System.getProperty("java.class.path"));
        System.out.println("os_name:" + System.getProperty("os.name"));
        System.out.println("os_arch:" + System.getProperty("os.arch"));
        System.out.println("os_version:" + System.getProperty("os.version"));
        System.out.println("user_name:" + System.getProperty("user.name"));
        System.out.println("user_home:" + System.getProperty("user.home"));
        System.out.println("user_dir:" + System.getProperty("user.dir"));
        System.out.println("java_vm_specification_version:"
                + System.getProperty("java.vm.specification.version"));
        System.out.println("java_vm_specification_vendor:"
                + System.getProperty("java.vm.specification.vendor"));
        System.out.println("java_vm_specification_name:"
                + System.getProperty("java.vm.specification.name"));
        System.out.println("java_vm_version:"
                + System.getProperty("java.vm.version"));
        System.out.println("java_vm_vendor:"
                + System.getProperty("java.vm.vendor"));
        System.out
                .println("java_vm_name:" + System.getProperty("java.vm.name"));
        System.out.println("java_ext_dirs:"
                + System.getProperty("java.ext.dirs"));
        System.out.println("file_separator:"
                + System.getProperty("file.separator"));
        System.out.println("path_separator:"
                + System.getProperty("path.separator"));
        System.out.println("line_separator:"
                + System.getProperty("line.separator"));
    }

}
