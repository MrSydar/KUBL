package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.service.csv.CSVInvoiceReader;
import com.mrsydar.kubl.engine.service.exceptions.BadCSVInvoiceRowException;
import com.mrsydar.kubl.engine.service.k360.K360Manager;
import com.mrsydar.kubl.engine.service.k360.K360NetworkLoader;
import com.mrsydar.kubl.engine.service.util.Invoices;
import com.mrsydar.kubl.engine.structures.csv.CSVInvoice;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class KUBLManager {
    private final K360Manager k360Manager;

    public KUBLManager(String apiId, String apiKey) {
        k360Manager = new K360Manager(
                apiId,
                new K360NetworkLoader(
                        apiId,
                        apiKey
                )
        );
    }

    public KUBLManager(K360Manager k360Manager) {
        this.k360Manager = k360Manager;
    }

//    public void loadInvoicesToK360(Path csvInvoicesURI) throws IOException, CsvValidationException, BadCSVInvoiceRowException {
//        CSVInvoiceReader reader = new CSVInvoiceReader(
//            Files.newBufferedReader(csvInvoicesURI)
//        );
//
//        boolean invoicesAvailable = true;
//        while(invoicesAvailable) {
//            CSVInvoice csvInvoice = reader.readNextInvoice();
//            if(csvInvoice != null) {
//
//
//                String customerId;
//
//                Invoices.csvInvoiceToInvoice(
//                        csvInvoice,
//
//                );
//            } else {
//                invoicesAvailable = false;
//            }
//        }
//    }
}
