package com.mrsydar.kubl.engine.service.util;

import com.mrsydar.kubl.engine.structures.csv.CSVInvoice;
import com.mrsydar.kubl.engine.structures.invoice.*;

public class Invoices {

    private Invoices() {
        throw new IllegalStateException("Utility class");
    }

    public static Invoice csvInvoiceToInvoice(
            CSVInvoice csvInvoice,
            String customerId,
            String taxId,
            String itemCode,
            String itemDescription,
            String itemQuantity
    ) {
        return new Invoice(
                new Customer(customerId),
                csvInvoice.getIssueDate(),
                csvInvoice.getIssueDate(),
                csvInvoice.getIssueDate(),
                csvInvoice.getInvoiceNo(),
                new InvoiceRow[]{
                        new InvoiceRow(
                            new Item(
                                    itemCode,
                                    itemDescription
                            ),
                                itemQuantity,
                                csvInvoice.getGrossValue(),
                                taxId
                        )
                },
                csvInvoice.getGrossValue(),
                new TaxAmount[]{
                        new TaxAmount(
                                taxId,
                                csvInvoice.getVatValue()
                        )
                }
        );
    }

}
