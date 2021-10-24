package com.mrsydar.kubl.engine.structures.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Item {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Description")
    private String description;

    public Item(String code, String description) {
        this.code = code;
        this.description = description;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code.equals(item.code) && description.equals(item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description);
    }
}

