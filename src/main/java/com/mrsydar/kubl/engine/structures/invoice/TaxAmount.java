package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TaxAmount {

    @JsonProperty("TaxId")
    private String taxId;

    @JsonProperty("Amount")
    private String amount;

    public TaxAmount(String taxId, String amount) {
        this.taxId = taxId;
        this.amount = amount;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxAmount taxAmount = (TaxAmount) o;
        return taxId.equals(taxAmount.taxId) && amount.equals(taxAmount.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, amount);
    }
}