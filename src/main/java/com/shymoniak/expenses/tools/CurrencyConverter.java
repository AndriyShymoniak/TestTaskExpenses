package com.shymoniak.expenses.tools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CurrencyConverter {

    public Map<String, Double> getExchangeValues() throws IOException {
        HashMap<String, Object> hashMap = getExchangerResponse();
        List<String> list =
                Arrays.stream(hashMap.get("rates").toString()
                        .replaceAll("\\{|\\}|\\s", "")
                        .split(","))
                        .collect(Collectors.toList());
        Map<String, Double> exchangeRate = new TreeMap<>(String::compareTo);
        String[] splitted;
        for (String str : list) {
            splitted = str.split("=");
            exchangeRate.put(splitted[0], Double.valueOf(splitted[1]));
        }
        return exchangeRate;
    }

    private HashMap<String, Object> getExchangerResponse() throws IOException {
        String url_str = "https://api.exchangerate.host/latest";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root =
                jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObj = root.getAsJsonObject();
        return new Gson().fromJson(jsonObj.toString(), HashMap.class);
    }
}
