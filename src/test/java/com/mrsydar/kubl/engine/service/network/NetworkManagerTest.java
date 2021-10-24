package com.mrsydar.kubl.engine.service.network;

import com.mrsydar.kubl.engine.service.network.NetworkManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class NetworkManagerTest {

    private CloseableHttpResponse response;
    private NetworkManager networkManager;

    @BeforeEach
    public void setup() throws IOException {
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        response = mock(CloseableHttpResponse.class, RETURNS_DEEP_STUBS);

        when(response.getEntity().getContent().readAllBytes())
                .thenReturn("expected output".getBytes(StandardCharsets.UTF_8));
        when(client.execute(any(HttpGet.class))).thenReturn(response);

        networkManager = new NetworkManager(client);
    }

    @Test
    public void getCode199() {
        when(response.getStatusLine().getStatusCode()).thenReturn(199);
        assertThrows(RuntimeException.class, () -> networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));
    }

    @Test
    public void getCode300() {
        when(response.getStatusLine().getStatusCode()).thenReturn(300);
        assertThrows(RuntimeException.class, () -> networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));
    }

    @Test
    public void getCode200() throws IOException {
        when(response.getStatusLine().getStatusCode()).thenReturn(200);
        assertEquals("expected output", networkManager.get("https://program.360ksiegowosc.pl/api/v1/"));
    }

}