package com.mrsydar.kubl.engine.service.hash;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HasherTest {

    private static String apiId;
    private static String apiKey;
    private static String requestBody;

    @BeforeAll
    public static void setup() throws IOException {
        apiId = Files.readString(
                Path.of("src/test/resources/hasher/api_id.txt"),
                StandardCharsets.UTF_8
        );
        apiKey = Files.readString(
                Path.of("src/test/resources/hasher/api_key.txt"),
                StandardCharsets.UTF_8
        );
        requestBody = Files.readString(
                Path.of("src/test/resources/hasher/data.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    public void getSignature() {
        String timestamp = "20210927085128";
        String dataString = apiId + timestamp + requestBody;

        assertEquals(
                "l0VfYVZImjklxXkB3fIgBBdQHzwFe77KgherXL8kRAc=",
                Hasher.calcHmacSha256(apiKey, dataString)
        );
    }

}