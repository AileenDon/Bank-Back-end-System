package com.usman.csudh.bank.core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class Load{
    // Read from file or webservice
    public Map<String, Double> currencySource() throws IOException, InterruptedException{
        InputStream inputStream = getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Double> exchangeRates = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {
            exchangeRates.putAll(parseExchangeRates(line));
        }return exchangeRates;
    }
    public abstract InputStream getInputStream() throws IOException, InterruptedException;
    public Map<String, Double> parseExchangeRates(String line) {
        Map<String, Double> exchangeRates = new HashMap<>();
        String[] parts = line.split(",");
        if (parts.length == 3) {
            String currencyCode = parts[0].trim();
            double exchangeRate = Double.parseDouble(parts[2].trim());
            exchangeRates.put(currencyCode, exchangeRate);
        }
        return exchangeRates;
    }
}

