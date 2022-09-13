import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpClientTest {

    @Test
    void shouldGetSuccessfulResponseCode() throws IOException {
        HttpClient client = new HttpClient("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }
}