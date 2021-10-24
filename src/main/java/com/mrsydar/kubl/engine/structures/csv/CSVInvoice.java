package com.mrsydar.kubl.engine.structures.csv;

import java.util.Objects;

public class CSVInvoice {
    private final String invoiceNo;
    private final String issueDate;
    private final String clientName;
    private final String clientNip;
    private final String netValue;
    private final String vatRate;
    private final String vatValue;
    private final String grossValue;

    public CSVInvoice(String invoiceNo, String issueDate, String clientName, String clientNip, String netValue, String vatRate, String vatValue, String grossValue) {
        this.invoiceNo = invoiceNo;
        this.issueDate = issueDate;
        this.clientName = clientName;
        this.clientNip = clientNip;
        this.netValue = netValue;
        this.vatRate = vatRate;
        this.vatValue = vatValue;
        this.grossValue = grossValue;
    }

    public String getClientName() {
        return clientName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getClientNip() {
        return clientNip;
    }

    public String getNetValue() {
        return netValue;
    }

    public String getVatRate() {
        return vatRate;
    }

    public String getVatValue() {
        return vatValue;
    }

    public String getGrossValue() {
        return grossValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVInvoice that = (CSVInvoice) o;
        return invoiceNo.equals(that.invoiceNo) && issueDate.equals(that.issueDate) && clientName.equals(that.clientName) && Objects.equals(clientNip, that.clientNip) && netValue.equals(that.netValue) && vatRate.equals(that.vatRate) && vatValue.equals(that.vatValue) && grossValue.equals(that.grossValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNo, issueDate, clientName, clientNip, netValue, vatRate, vatValue, grossValue);
    }
}
