package com.shymoniak.expenses.tools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Component
public class CurrencyConverter {
    public Map<String, Double> getExchangeValues() throws IOException {
        URL url = new URL("https://api.exchangerate.host/latest");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root =
                jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObj = root.getAsJsonObject();
        return new Gson().fromJson(jsonObj.get("rates").toString(), LinkedTreeMap.class);
    }
}
