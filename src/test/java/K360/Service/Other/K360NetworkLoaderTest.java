package K360.Service.Other;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class K360NetworkLoaderTest {

    private static String apiId;
    private static String apiKey;
    private static String k360InvoiceJson;

    @BeforeAll
    public static void mockObjects() throws IOException {
        apiId = Files.readString(
                Path.of("src/test/resources/k360_network_loader/api_id.txt"),
                StandardCharsets.UTF_8
        );

        apiKey = Files.readString(
                Path.of("src/test/resources/k360_network_loader/api_key.txt"),
                StandardCharsets.UTF_8
        );

        k360InvoiceJson = Files.readString(
                Path.of("src/test/resources/k360_network_loader/data.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    public void post() throws IOException {
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        CloseableHttpResponse response = mock(CloseableHttpResponse.class, RETURNS_DEEP_STUBS);

        when(response.getEntity().getContent().readAllBytes())
                .thenReturn("\"{\\\"Obj\\\":\\\"expected response\\\"}\"".getBytes(StandardCharsets.UTF_8));

        when(client.execute(any(HttpGet.class))).thenReturn(response);

        when(response.getStatusLine().getStatusCode()).thenReturn(199);
        assertThrows(RuntimeException.class, () -> {
            K360NetworkLoader networkManager = new K360NetworkLoader(apiId, apiKey, client);
            networkManager.post("https://program.360ksiegowosc.pl/api/v1/sendinvoice", k360InvoiceJson);
        });

        when(response.getStatusLine().getStatusCode()).thenReturn(300);
        assertThrows(RuntimeException.class, () -> {
            K360NetworkLoader networkManager = new K360NetworkLoader(apiId, apiKey, client);
            networkManager.post("https://program.360ksiegowosc.pl/api/v1/sendinvoice", k360InvoiceJson);
        });

        when(response.getStatusLine().getStatusCode()).thenReturn(200);

        K360NetworkLoader networkManager = new K360NetworkLoader(apiId, apiKey, client);
        assertEquals("{\"Obj\":\"expected response\"}", networkManager.post("https://program.360ksiegowosc.pl/api/v1/sendinvoice", k360InvoiceJson));
    }

}