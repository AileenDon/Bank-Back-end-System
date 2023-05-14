package com.usman.csudh.bank.core;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoadFromRest extends Load {
    @Override
    public InputStream getInputStream() throws IOException {
        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=aa2eeac576bc47e3a8a71b33f8844651");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return conn.getInputStream();
    }
    public Map<String, Double> parseExchangeRates(String line) {
        Map<String, Double> exchangeRates = new HashMap<>();

        int start = line.indexOf("\"");
        int end = line.lastIndexOf("\"");

        if (start < 0 || end < 0 || start >= end) {
            return exchangeRates;
        }

        String currencyCode = line.substring(start + 1, end);

        start = line.indexOf(":");
        end = line.indexOf(",");

        if (start < 0 || end < 0 || start >= end) {
            return exchangeRates;
        }

        String exchangeRateStr = line.substring(start + 1, end);

        try {
            double exchangeRate = Double.parseDouble(exchangeRateStr);
            exchangeRates.put(currencyCode, exchangeRate);
        } catch (NumberFormatException e) {
        }return exchangeRates;
    }

}
