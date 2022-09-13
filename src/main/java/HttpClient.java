import com.sun.source.tree.Tree;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class HttpClient {

    private final int statusCode;
    private final Map<String, String> headerMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public HttpClient(String host, int port, String target) throws IOException {
        Socket socket = new Socket(host, port);
        String request = "GET " + target + " HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: " + host + "\r\n" +
                "\r\n";

        socket.getOutputStream().write(request.getBytes());


        String line = readLine(socket);
        statusCode = Integer.parseInt(line.split(" ")[1]);
        //System.out.println(line);
        //Header

        while(!(line = readLine(socket)).isEmpty()){
            System.out.println(line);
            String[] header = line.split(":\\s*");
            headerMap.put(header[0], header[1]);
        }

    }

    private String readLine(Socket socket) throws IOException {
        int c;
        StringBuilder line = new StringBuilder();
        while ((c = socket.getInputStream().read()) != '\r') {
            line.append((char) c);
        }
        socket.getInputStream().read();
        return line.toString();
    }


    public int getStatusCode() {
        return statusCode;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("httpbin.org", 80);
        String request = "GET /html HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: httpbin.org\n" +
                "\r\n";

        socket.getOutputStream().write(request.getBytes());


        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            System.out.print((char) c);
        }
    }

    public String getHeader(String fieldName) {

        return null;
    }
}
