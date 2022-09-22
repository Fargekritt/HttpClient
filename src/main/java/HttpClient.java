import com.sun.source.tree.Tree;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class HttpClient {

    private final int statusCode;
    private final HttpMessage response;


    public HttpClient(String host, int port, String target) throws IOException {
        Socket socket = new Socket(host, port);
        String request = "GET " + target + " HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: " + host + "\r\n" +
                "\r\n";


        socket.getOutputStream().write(request.getBytes());

        response = new HttpMessage(socket);
        statusCode = Integer.parseInt(response.getStartLine().split(" ")[1]);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String fieldName) {
        return response.getHeader(fieldName);
    }

    public int getContentLength() {
        return response.contentLength;
    }
    
    public String getBody(){
        return response.body;
    }

}
