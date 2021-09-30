package K360.Structures.Other;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Customer")
public class FullCustomer {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("VatRegNo")
    private String vatRegNo;

    @JsonProperty("CountryCode")
    private String countryCode;

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public FullCustomer(String name, String vatRegNo, String countryCode) {
        this.name = name;
        this.vatRegNo = vatRegNo;
        this.countryCode = countryCode;
    }
}