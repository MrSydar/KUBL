package com.mrsydar.kubl.engine.structures.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Customer")
public class NamedCustomer {

    @JsonProperty("Name")
    String name;

    @JsonProperty("VatRegNo")
    String vatRegNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatRegNo() {
        return vatRegNo;
    }

    public void setVatRegNo(String vatRegNo) {
        this.vatRegNo = vatRegNo;
    }

    public NamedCustomer(String name, String vatRegNo) {
        this.name = name;
        this.vatRegNo = vatRegNo;
    }

}
