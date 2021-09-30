package K360.Service.Other;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

public class TimeManager {

    private NetworkManager networkManager;

    public LocalDate requestCurrentPolishDate() throws IOException {
        String responseBodyStr = networkManager.get(
                "http://worldtimeapi.org/api/timezone/Europe/Warsaw"
        );

        JSONObject responseJson = new JSONObject(responseBodyStr);
        String dateStr = responseJson.getString("datetime").substring(0, 10);

        return LocalDate.parse(dateStr);
    }

    public TimeManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

}
