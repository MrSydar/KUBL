package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.service.k360.CustomerManager;
import com.mrsydar.kubl.engine.structures.customer.CompleteCustomer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerManagerTest {

    private static String expectedResponseBody;

    @BeforeAll
    public static void loadExpectedResponseBody() throws IOException {
        expectedResponseBody = Files.readString(
                Path.of("src/test/resources/customer_manager/data_for_nip_5252248481.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    public void getCustomerByNIP() throws IOException {
        NetworkManager networkManager = mock(NetworkManager.class);
        TimeManager timeManager = mock(TimeManager.class);

        when(
                networkManager.get(
                        eq("https://wl-api.mf.gov.pl/api/search/nip/5252248481?date=2021-09-26")
                )
        ).thenReturn(expectedResponseBody);

        when(timeManager.requestCurrentPolishDate()).thenReturn(LocalDate.parse("2021-09-26"));

        CompleteCustomer customerWarsaw = new CustomerManager(networkManager, timeManager).getCustomerByNIP("5252248481");

        assertEquals("MIASTO STOŁECZNE WARSZAWA", customerWarsaw.getName());
        assertEquals("5252248481", customerWarsaw.getNip());
        assertEquals("015259640", customerWarsaw.getRegon());
        assertEquals("PL. BANKOWY 3/5", customerWarsaw.getStreet());
        assertEquals("00-950", customerWarsaw.getPostalCode());
        assertEquals("WARSZAWA", customerWarsaw.getCity());
        assertEquals("PL", customerWarsaw.getCountryCode());
    }

}