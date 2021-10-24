package com.mrsydar.kubl.engine.service.time;

import com.mrsydar.kubl.engine.service.network.NetworkManager;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

public class TimeManager {

    private final NetworkManager networkManager;

    public TimeManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public LocalDate requestCurrentPolishDate() throws IOException {
        String responseBodyStr = networkManager.get(
                "http://worldtimeapi.org/api/timezone/Europe/Warsaw"
        );

        JSONObject responseJson = new JSONObject(responseBodyStr);
        String dateStr = responseJson.getString("datetime").substring(0, 10);

        return LocalDate.parse(dateStr);
    }

}
