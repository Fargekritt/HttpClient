import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class HttpMessage {

    private final String startLine;
    private final Map<String, String> headers;
    public int contentLength;
    public String body;

    public HttpMessage(Socket socket) throws IOException {
        startLine = readLine(socket);
        headers = readHeader(socket);
        if(getHeader("Content-length") != null){
            contentLength = Integer.parseInt(getHeader("Content-length"));
            body = readBody(socket);
        }
    }

    private String readBody(Socket socket) throws IOException {
        var body = new byte[contentLength];
        for (int i = 0; i < body.length; i++) {
            body[i] = (byte) socket.getInputStream().read();
        }
        return new String(body, StandardCharsets.UTF_8);
    }

    public String getHeader(String fieldName) {
        return headers.get(fieldName);
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

    public String getStartLine() {
        return startLine;
    }

    private Map<String, String> readHeader(Socket socket) throws IOException {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        String headerLine;
        while(!(headerLine = readLine(socket)).isEmpty()){
            System.out.println(headerLine);
            String[] header = headerLine.split(":\\s*");
            headers.put(header[0], header[1]);
        }
        return headers;
    }
}
