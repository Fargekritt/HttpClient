import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {

    private final ServerSocket serverSocket;

    public HttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) throws IOException {
        new HttpServer(8080).start();
    }

    private void start() throws IOException {

        Socket socket = serverSocket.accept();

        socket.getOutputStream().write(("HTTP(1.1 200 OK\r\n" +
                "Content-length: 12\r\n" +
                "Content-type: text/plain\r\n" +
                "\r\n" +
                "Hello World!").getBytes(StandardCharsets.UTF_8));
    }


}
