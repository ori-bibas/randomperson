package com.example.randompersonapi;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@Service
public class PersonJsonService {

    private static final JSONParser jsonParser = new JSONParser();
    private static final String firstNamesURL = "https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/first-names.json";
    private static final String lastNamesURL = "https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/last-names.json";
    private static final String addressesURL = "https://raw.githubusercontent.com/EthanRBrown/rrad/master/addresses-us-100.json";
    private static JSONArray firstNames = null;
    private static JSONArray lastNames = null;
    private static JSONArray addresses = null;

    public PersonJsonService() throws IOException, ParseException {
        JSONObject firstNamesObj = getJSONObjectFromRemote(firstNamesURL);
        JSONObject lastNamesObj = getJSONObjectFromRemote(lastNamesURL);
        JSONObject emailsObj = getJSONObjectFromRemote(addressesURL);
        firstNames = (JSONArray) firstNamesObj.get("firstNames");
        lastNames = (JSONArray) lastNamesObj.get("lastNames");
        addresses = (JSONArray) emailsObj.get("addresses");
    }

    public static JSONArray getFirstNames() {
        return firstNames;
    }

    public static JSONArray getLastNames() {
        return lastNames;
    }

    public static JSONArray getAddresses() {
        return addresses;
    }

    public JSONObject getJSONObjectFromRemote(String link) throws IOException, ParseException {

        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Read in the response to StringBuffer
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the response, and set a JSONArray to the array "addresses"
        JSONObject obj = (JSONObject) jsonParser.parse(response.toString());

        return obj;
    }
}
