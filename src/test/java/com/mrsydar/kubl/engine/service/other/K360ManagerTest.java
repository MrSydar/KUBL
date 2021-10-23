package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.service.k360.K360Manager;
import com.mrsydar.kubl.engine.service.k360.K360NetworkLoader;
import com.mrsydar.kubl.engine.structures.invoice.Customer;
import com.mrsydar.kubl.engine.structures.invoice.Invoice;
import com.mrsydar.kubl.engine.structures.invoice.InvoiceRow;
import com.mrsydar.kubl.engine.structures.invoice.TaxAmount;
import com.mrsydar.kubl.engine.structures.customer.FullCustomer;
import com.mrsydar.kubl.engine.structures.customer.NamedCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

class K360ManagerTest {

    private static String customerIdListUrl;
    private static String customerAddUrl;
    private static String invoiceAddUrl;

    private static K360NetworkLoader loader;
    private static K360Manager manager;

    @BeforeAll
    public static void setup() throws IOException {
        String apiId = Files.readString(
                Path.of("src/test/resources/k360_manager/api_id.txt"),
                StandardCharsets.UTF_8
        );

        customerIdListUrl = String.format("https://program.360ksiegowosc.pl/api/v1/getcustomers?ApiId=%s", apiId);
        customerAddUrl = String.format("https://program.360ksiegowosc.pl/api/v2/sendcustomer?ApiId=%s", apiId);
        invoiceAddUrl = String.format("https://program.360ksiegowosc.pl/api/v1/sendinvoice?ApiId=%s", apiId);

        loader = Mockito.mock(K360NetworkLoader.class);
        manager = new K360Manager(apiId, loader);
    }

    @Test
    void getCustomerId() throws IOException {
        String customerName = "John Smith";
        String customerVatRegNo = "000000000";

        String customerJsonStr = new ObjectMapper().writeValueAsString(
                new NamedCustomer(customerName, customerVatRegNo)
        );

        String johnSmithId = "eb3e62e2-21f9-11ec-9621-0242ac130002";
        ObjectMapper mapper = new ObjectMapper();
        when(loader.post(
                eq(customerIdListUrl),
                eq(customerJsonStr)
                )
        ).thenReturn(
                mapper.createArrayNode().add(
                        mapper.createObjectNode().put("CustomerId", johnSmithId)
                ).toString()
        );

        ArrayList<String> clientId = new ArrayList<>();
        clientId.add(johnSmithId);

        assertEquals(
                clientId,
                manager.getCustomerId(customerName, customerVatRegNo)
        );
    }

    @Test
    void postCustomer() throws IOException {
        FullCustomer fullCustomer = new FullCustomer(
                "John Smith",
                "000000000",
                "PL"
        );
        String johnSmithId = "eb3e62e2-21f9-11ec-9621-0242ac130002";
        String customerJsonStr = new ObjectMapper().writeValueAsString(fullCustomer);

        ObjectMapper mapper = new ObjectMapper();
        when(loader.post(
                        eq(customerAddUrl),
                        eq(customerJsonStr)
                )
        ).thenReturn(
                mapper.createObjectNode().put("Id", johnSmithId).toString()
        );

        assertEquals(new Customer(johnSmithId).getId(), manager.postCustomer(fullCustomer).getId());
    }

    @Test
    void postInvoice() throws IOException {
        Invoice invoice = new Invoice(
                new Customer(
                        "ee98165e-21f9-11ec-9621-0242ac130002"
                ),
                "20210930000000",
                "20210930000000",
                "20210930000000",
                "TEST-INVOICE-01",
                new InvoiceRow[]{},
                "404",
                new TaxAmount[]{}
        );

        manager.postInvoice(invoice);

        verify(loader).post(
                invoiceAddUrl,
                new ObjectMapper().writeValueAsString(invoice)
        );
    }

}