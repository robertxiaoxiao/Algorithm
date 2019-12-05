package TomcatLearning.BasicServelet;/*
 * @author:
 * @date:  2019/12/4/004
 * @description:
 */

import javax.net.ssl.SSLSession;
import javax.servlet.*;
import java.io.*;
import java.net.URI;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Response implements ServletRequest {
    private OutputStream output;
    private Request request;
    PrintWriter writer;
    private static final int buffersize = 1024;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setrequest(Request request) {
        this.request = request;
    }

    public void sendstatisticresource() throws IOException {
        byte[] bytes = new byte[buffersize];
        FileInputStream fip = null;
        try {
            File file = new File(Server.WEB_ROOT, request.geturl());
            if (file.exists()) {
                fip = new FileInputStream(file);
                // read/write file 1025 bytes at each time
                int ch = fip.read(bytes, 0, buffersize);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fip.read(bytes, 0, buffersize);
                }
            } else {
                String errormessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>file not found</h1>";
                output.write(errormessage.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the finputstream ;
            if (fip != null)
                fip.close();
        }
    }

    public PrintWriter getWriter() {
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public long getContentLengthLong() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
