package com.mrsydar.kubl.engine.structures.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Customer")
public class CompleteCustomer {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("VatRegNo")
    private String nip;

    @JsonProperty("RefNoBase")
    private String regon;

    @JsonProperty("VatRegNo")
    private String street;

    @JsonProperty("PostalCode")
    private String postalCode;

    @JsonProperty("City")
    private String city;

    @JsonProperty("CountryCode")
    private String countryCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CompleteCustomer(String name, String nip, String regon, CustomerAddress customerAddressInfo, String countryCode) {
        this.name = name;
        this.nip = nip;
        this.regon = regon;
        this.street = customerAddressInfo.getStreet();
        this.postalCode = customerAddressInfo.getPostalCode();
        this.city = customerAddressInfo.getCity();
        this.countryCode = countryCode;
    }

}
