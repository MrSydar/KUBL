package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxAmount {

    @JsonProperty("TaxId")
    private String taxId;

    @JsonProperty("Amount")
    private String amount;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TaxAmount(String taxId, String amount) {
        this.taxId = taxId;
        this.amount = amount;
    }

}