package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.service.hash.Hasher;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class K360NetworkLoader {

    private final String apiId;
    private final String apiKey;
    private final CloseableHttpClient client;

    public String post(String destUrl, String jsonObject) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String signature = Hasher.calcHmacSha256(apiKey, apiId + timestamp + jsonObject);
        destUrl = destUrl + String.format("&timestamp=%s&signature=%s", timestamp, signature);

        HttpPost httpPost = new HttpPost(destUrl);

        StringEntity requestPayload = new StringEntity(jsonObject, "UTF-8");
        requestPayload.setContentType("application/json");

        httpPost.setEntity(requestPayload);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        int statusCode;
        String reasonPhrase;
        String dirtyResponseBody;

        try (CloseableHttpResponse response = client.execute(httpPost)) {
            statusCode = response.getStatusLine().getStatusCode();
            reasonPhrase = response.getStatusLine().getReasonPhrase();

            dirtyResponseBody = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        }

        if (statusCode < 200 || statusCode > 299) {
            throw new RuntimeException(String.format("Bad response code: %d\nReason: %s", statusCode, reasonPhrase));
        } else return dirtyResponseBody.substring(1, dirtyResponseBody.length() - 1).replace("\\", "");    //TODO: com.mrsydar.K360 returns responce body with extra characters, why ?
    }

    public K360NetworkLoader(String apiId, String apiKey, CloseableHttpClient client) {
        this.apiId = apiId;
        this.apiKey = apiKey;
        this.client = client;
    }

}
