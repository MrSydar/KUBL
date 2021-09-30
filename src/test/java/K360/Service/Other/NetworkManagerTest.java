package K360.Service.Other;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class NetworkManagerTest {

    @Test
    void get() throws IOException {
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        CloseableHttpResponse response = mock(CloseableHttpResponse.class, RETURNS_DEEP_STUBS);

        when(response.getEntity().getContent().readAllBytes())
                .thenReturn("expected output".getBytes(StandardCharsets.UTF_8));
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        NetworkManager networkManager = new NetworkManager(client);

        when(response.getStatusLine().getStatusCode()).thenReturn(199);
        assertThrows(RuntimeException.class, () -> networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));

        when(response.getStatusLine().getStatusCode()).thenReturn(300);
        assertThrows(RuntimeException.class, () -> networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));

        when(response.getStatusLine().getStatusCode()).thenReturn(200);
        assertEquals("expected output", networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));
    }

}