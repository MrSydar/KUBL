package com.mrsydar.kubl.engine.service.util;

import com.mrsydar.kubl.engine.structures.csv.CSVInvoice;
import com.mrsydar.kubl.engine.structures.invoice.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InvoicesTest {

    @Test
    void csvInvoiceToInvoice() {
        String customerId = "0146195a-6c78-4bb9-8373-b1a90ac07631";
        String invoiceFormattedDate = "20211012121310";
        String invoiceNo = "DGJGGGDT-03-2021-1155612";
        String itemCode = "20";
        String itemQuantity = "1";
        String itemDescription = "Usługa transportowa";
        String invoiceGross = "19.5";
        String taxId = "aa539c5c-6aae-4fa6-ac96-f8d02164cb3f";
        String taxAmount = "1.56";

        Invoice expected = new Invoice(
                new Customer(customerId),
                invoiceFormattedDate,
                invoiceFormattedDate,
                invoiceFormattedDate,
                invoiceNo,
                new InvoiceRow[] {
                        new InvoiceRow(
                                new Item(
                                        itemCode,
                                        itemDescription
                                ),
                                itemQuantity,
                                invoiceGross,
                                taxId
                        )
                },
                invoiceGross,
                new TaxAmount[]{
                        new TaxAmount(
                            taxId,
                            taxAmount
                        )
                }
        );

        CSVInvoice csvInvoice = new CSVInvoice(
                invoiceNo,
                invoiceFormattedDate,
                "John Smith",
                "1234567890",
                "17.94",
                "0.08",
                taxAmount,
                invoiceGross
        );

        Invoice actual = Invoices.csvInvoiceToInvoice(
                csvInvoice,
                customerId,
                taxId,
                itemCode,
                itemDescription,
                itemQuantity
        );

        assertEquals(expected, actual);
    }
}