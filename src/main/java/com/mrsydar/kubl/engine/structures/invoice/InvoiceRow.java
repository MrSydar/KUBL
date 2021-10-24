package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class InvoiceRow {

    @JsonProperty("TaxId")
    private String taxId;

    @JsonProperty("Item")
    private Item item;

    @JsonProperty("Quantity")
    private String quantity;

    @JsonProperty("Price")
    private String price;

    public InvoiceRow(com.mrsydar.kubl.engine.structures.invoice.Item item, String quantity, String price, String taxId) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.taxId = taxId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceRow that = (InvoiceRow) o;
        return taxId.equals(that.taxId) && item.equals(that.item) && quantity.equals(that.quantity) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, item, quantity, price);
    }
}
