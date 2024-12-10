package com.example.currencyconverter;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.Scanner;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

public class CurrencyConverter {
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String USER_AGENT_ID = "Java/" + System.getProperty("java.version");

    public static double rate(Currency from, Currency to) throws IOException {
        String queryPath = "https://free.currconv.com/api/v7/convert?q="
                + from.getCurrencyCode() + "_"
                + to.getCurrencyCode()
                + "&compact=ultra&apiKey=" + API_KEY;

        URL queryURL = new URL(queryPath);
        HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) { // 200 is HTTP status OK
            try (InputStream stream = connection.getInputStream();
                 Scanner scanner = new Scanner(stream)) {
                    String quote = scanner.nextLine();
                    String number = quote.substring(quote.indexOf(':') + 1,
                        quote.indexOf('}'));
                    return Double.parseDouble(number);
//                String jsonResponse = scanner.useDelimiter("\\A").next();
//                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
//                String conversionKey = from.getCurrencyCode() + "_" + to.getCurrencyCode();
//                return jsonObject.get(conversionKey).getAsDouble();
            }
        } else {
            throw new IOException("Query to " + queryPath + " failed with HTTP status " + responseCode);
        }
    }
}
