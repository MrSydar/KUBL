package K360.Service.Other;

import K360.Structures.Other.CompleteCustomer;
import K360.Structures.Other.CustomerAddress;
import org.json.JSONObject;

import java.io.IOException;

public class CustomerManager {

    private final NetworkManager networkManager;
    private final TimeManager timeManager;

    public CustomerManager(NetworkManager networkManager, TimeManager timeManager) {
        this.networkManager = networkManager;
        this.timeManager = timeManager;
    }

    private CustomerAddress parseCustomerAddress(String unparsedAddress) {
        int streetSeparatorIndex = unparsedAddress.lastIndexOf(',');

        String streetExcluded = unparsedAddress.substring(streetSeparatorIndex + 2);
        int postalCodeSeparatorIndex = streetExcluded.indexOf(' ');

        return new CustomerAddress(
                unparsedAddress.substring(0, streetSeparatorIndex),
                streetExcluded.substring(0, postalCodeSeparatorIndex),
                streetExcluded.substring(postalCodeSeparatorIndex + 1)
        );
    }

    public CompleteCustomer getCustomerByNIP(String nip) throws IOException {
        String url = String.format("https://wl-api.mf.gov.pl/api/search/nip/%s?date=%tF", nip, timeManager.requestCurrentPolishDate());

        JSONObject jsonResponse = new JSONObject(networkManager.get(url));
        JSONObject jsonCustomer = jsonResponse.getJSONObject("result").getJSONObject("subject");

        String unparsedAddress;
        if (jsonCustomer.isNull("residenceAddress")) {
            unparsedAddress = jsonCustomer.getString("workingAddress");
        } else {
            unparsedAddress = jsonCustomer.getString("residenceAddress");
        }

        return new CompleteCustomer(
                jsonCustomer.getString("name"),
                jsonCustomer.getString("nip"),
                jsonCustomer.getString("regon"),
                parseCustomerAddress(unparsedAddress),
                "PL"
        );
    }

}
