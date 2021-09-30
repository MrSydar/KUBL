package K360.Structures.Invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceRow {

    @JsonProperty("TaxId")
    private String taxId;

    @JsonProperty("Item")
    private Item item;

    @JsonProperty("Quantity")
    private String quantity;

    @JsonProperty("Price")
    private String price;

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

    public InvoiceRow(K360.Structures.Invoice.Item item, String quantity, String price, String taxId) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.taxId = taxId;
    }

}
