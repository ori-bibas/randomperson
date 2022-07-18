package com.example.randompersonapi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonJsonServiceTest {

    PersonJsonService personJsonService = new PersonJsonService();

    PersonJsonServiceTest() throws IOException, ParseException {}

    @Test
    public void getJSONObjectFromRemoteTest() throws IOException, ParseException {

        JSONObject firstNamesObj = personJsonService.getJSONObjectFromRemote("https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/first-names.json");
        assertNotNull(firstNamesObj);

        JSONObject lastNamesObj = personJsonService.getJSONObjectFromRemote("https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/last-names.json");
        assertNotNull(lastNamesObj);

        JSONObject emailsObj = personJsonService.getJSONObjectFromRemote("https://raw.githubusercontent.com/EthanRBrown/rrad/master/addresses-us-100.json");
        assertNotNull(emailsObj);
    }
}