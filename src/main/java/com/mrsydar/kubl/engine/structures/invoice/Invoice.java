package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {

    @JsonProperty("Customer")
    private Customer customer;

    @JsonProperty("DocDate")
    private String docDate;

    @JsonProperty("DueDate")
    private String dueDate;

    @JsonProperty("TransactionDate")
    private String transactionDate;

    @JsonProperty("InvoiceNo")
    private String invoiceNo;

    @JsonProperty("InvoiceRow")
    private InvoiceRow[] invoiceRows;

    @JsonProperty("TaxAmount")
    private TaxAmount[] taxAmounts;

    @JsonProperty("TotalAmount")
    private String totalAmount;

    public Invoice(Customer customer, String docDate, String dueDate, String transactionDate, String invoiceNo, InvoiceRow[] invoiceRow, String totalAmount, TaxAmount[] taxAmount) {
        this.customer = customer;
        this.docDate = docDate;
        this.dueDate = dueDate;
        this.transactionDate = transactionDate;
        this.invoiceNo = invoiceNo;
        this.invoiceRows = invoiceRow;
        this.totalAmount = totalAmount;
        this.taxAmounts = taxAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public InvoiceRow[] getInvoiceRows() {
        return invoiceRows;
    }

    public void setInvoiceRows(InvoiceRow[] invoiceRows) {
        this.invoiceRows = invoiceRows;
    }

    public TaxAmount[] getTaxAmounts() {
        return taxAmounts;
    }

    public void setTaxAmounts(TaxAmount[] taxAmounts) {
        this.taxAmounts = taxAmounts;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

}
