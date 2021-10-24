package com.mrsydar.kubl.engine.service.csv;

import com.mrsydar.kubl.engine.service.exceptions.BadCSVInvoiceRowException;
import com.mrsydar.kubl.engine.structures.csv.CSVInvoice;
import com.mrsydar.kubl.engine.structures.invoice.Invoice;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CSVInvoiceReaderTest {

    @Test
    void readNextInvoice() throws URISyntaxException, IOException, CsvValidationException, BadCSVInvoiceRowException {
        BufferedReader bufferedReader = Files.newBufferedReader(
                Paths.get(ClassLoader.getSystemResource("csv/UBER_KUBL_EXPORT.csv").toURI())
        );

        CSVInvoiceReader invoiceReader = new CSVInvoiceReader(bufferedReader);

        CSVInvoice expectedInvoice1 = new CSVInvoice(
                "DGJBBGDT-01-2020-0000738",
                "20200127202001",
                "Magda Wysoka",
                null,
                "19.9",
                "0.08",
                "1.59",
                "21.49"
        );
        assertEquals(expectedInvoice1, invoiceReader.readNextInvoice());

        CSVInvoice expectedInvoice2 = new CSVInvoice(
                "DGJBBGDT-01-2020-0000744",
                "20200127202121",
                "Juliana Pąkke",
                "1234567890",
                "41.81",
                "0.08",
                "3.35",
                "45.16"
        );
        assertEquals(expectedInvoice2, invoiceReader.readNextInvoice());

        CSVInvoice expectedInvoice3 = new CSVInvoice(
                "DGJBBGDT-01-2020-0000751",
                "20200128214530",
                "Оксана Cереліва",
                null,
                "16.68",
                "0.08",
                "1.33",
                "18.01"
        );
        assertEquals(expectedInvoice3, invoiceReader.readNextInvoice());

        assertNull(invoiceReader.readNextInvoice());
    }

}