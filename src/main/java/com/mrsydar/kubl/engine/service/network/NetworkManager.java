package com.mrsydar.kubl.engine.service.network;

import com.mrsydar.kubl.engine.service.exceptions.NotSuccessResponseCodeExcetion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NetworkManager {

    private final CloseableHttpClient client;

    public NetworkManager(CloseableHttpClient client) {
        this.client = client;
    }

    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        int statusCode;
        String responseBodyStr;
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            statusCode = response.getStatusLine().getStatusCode();
            responseBodyStr = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        }

        if (statusCode < 200 || statusCode > 299) {
            throw new NotSuccessResponseCodeExcetion(String.format("Bad response code: %d", statusCode));
        } else return responseBodyStr;
    }

}
