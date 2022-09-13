import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    public HttpClient(String host, int port, String target) throws IOException {
        Socket socket = new Socket(host, port);
        String request = "GET " + target + " HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: " + host + "\n" +
                "\r\n";

        socket.getOutputStream().write(request.getBytes());


        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            System.out.print((char) c);
        }
    }



    public int getStatusCode() {
        return 0;
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
}
