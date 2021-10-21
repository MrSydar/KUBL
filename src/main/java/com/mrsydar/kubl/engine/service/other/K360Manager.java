package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.structures.invoice.Customer;
import com.mrsydar.kubl.engine.structures.invoice.Invoice;
import com.mrsydar.kubl.engine.structures.other.FullCustomer;
import com.mrsydar.kubl.engine.structures.other.NamedCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class K360Manager {

    private final K360NetworkLoader k360Loader;

    private final String customerIdListUrl;
    private final String customerAddUrl;
    private final String invoiceAddUrl;

    public K360Manager(String apiId, K360NetworkLoader k360Loader) {
        this.k360Loader = k360Loader;

        customerIdListUrl = String.format("https://program.360ksiegowosc.pl/api/v1/getcustomers?ApiId=%s", apiId);
        customerAddUrl = String.format("https://program.360ksiegowosc.pl/api/v2/sendcustomer?ApiId=%s", apiId);
        invoiceAddUrl = String.format("https://program.360ksiegowosc.pl/api/v1/sendinvoice?ApiId=%s", apiId);
    }

    public ArrayList<String> getCustomerId(String customerName, String customerVatRegNo) throws IOException {
        String customerJsonStr = new ObjectMapper().writeValueAsString(
                new NamedCustomer(customerName, customerVatRegNo)
        );
        String responseBody = k360Loader.post(customerIdListUrl, customerJsonStr);
        JSONArray jsonCustomersArray = new JSONArray(responseBody);

        ArrayList<String> customerId = new ArrayList<>();
        for (Object customerObj: jsonCustomersArray) {
            JSONObject customerJSON = (JSONObject) customerObj;
            customerId.add(customerJSON.getString("CustomerId"));
        }

        return customerId;
    }

    public Customer postCustomer(FullCustomer customer) throws IOException {
        String customerJsonStr = new ObjectMapper().writeValueAsString(customer);
        String responseBody = k360Loader.post(customerAddUrl, customerJsonStr);

        JSONObject addedCustomerData = new JSONObject(responseBody);

        return new Customer(addedCustomerData.getString("Id"));
    }

    public void postInvoice(Invoice invoice) throws IOException {
        String invoiceJsonStr = new ObjectMapper().writeValueAsString(invoice);
        k360Loader.post(invoiceAddUrl, invoiceJsonStr);
    }

}