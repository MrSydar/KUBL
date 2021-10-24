package com.mrsydar.kubl.engine.service.time;

import com.mrsydar.kubl.engine.service.network.NetworkManager;
import com.mrsydar.kubl.engine.service.time.TimeManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

class TimeManagerTest {

    private static String expectedResponseBody;

    @BeforeAll
    public static void loadExpectedResponseBody() throws IOException {
        expectedResponseBody = Files.readString(
                Path.of("src/test/resources/time_manager/data_for_warsaw_date.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    void requestCurrentPolishDate() throws IOException {
        NetworkManager networkManager = Mockito.mock(NetworkManager.class);

        when(networkManager.get(eq("http://worldtimeapi.org/api/timezone/Europe/Warsaw"))).thenReturn(expectedResponseBody);

        TimeManager timeManager = new TimeManager(networkManager);
        assertEquals(timeManager.requestCurrentPolishDate(), LocalDate.parse("2021-09-26"));
    }

}