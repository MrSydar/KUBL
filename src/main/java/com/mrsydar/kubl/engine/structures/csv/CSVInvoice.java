package com.mrsydar.kubl.engine.structures.csv;

public class CSVInvoice {
    private final String invoiceNo;
    private final String issueDate;
    private final String clientNip;
    private final Float netValue;
    private final Float vatRate;
    private final Float vatValue;
    private final Float grossValue;

    public CSVInvoice(String invoiceNo, String issueDate, String clientNip, Float netValue, Float vatRate, Float vatValue, Float grossValue) {
        this.invoiceNo = invoiceNo;
        this.issueDate = issueDate;
        this.clientNip = clientNip;
        this.netValue = netValue;
        this.vatRate = vatRate;
        this.vatValue = vatValue;
        this.grossValue = grossValue;
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

    public Float getNetValue() {
        return netValue;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public Float getVatValue() {
        return vatValue;
    }

    public Float getGrossValue() {
        return grossValue;
    }
}
