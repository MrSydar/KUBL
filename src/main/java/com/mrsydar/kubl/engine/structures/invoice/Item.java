package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

