package com.example.randompersonapi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RandomPersonFactoryTest {

    private static RandomPersonFactory personFactory = new RandomPersonFactory();

    @Test
    void generateRandomPhoneNumberTest() {
        String phoneNumber = personFactory.generateRandomPhoneNumber();
        assertTrue(phoneNumber.charAt(0) != '0');
        assertTrue(phoneNumber.charAt(0) != '1');
        assertTrue(phoneNumber.length() == 10);
    }

    @Test
    void createRandomAddressTest() throws IOException, ParseException {
        Address address = personFactory.createRandomAddress();
        assertNotNull(address);
        assertNotNull(address.streetAddress);
        assertNotNull(address.city);
        assertNotNull(address.state);
        assertNotNull(address.zipCode);
    }

    @Test
    void createEmailTest() {
        String email = personFactory.createEmail("Ori", "Bibas");
        assertNotNull(email);
        assertTrue(email.contains("ori"));
        assertTrue(email.contains("bibas"));
        assertTrue(email.contains("@"));
        assertTrue(email.contains(".com"));
    }

    @Test
    void getJSONObjectFromRemoteTest() throws IOException, ParseException {
        JSONObject obj = personFactory.getJSONObjectFromRemote("https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/first-names.json");
        assertNotNull(obj);
    }

    @Test
    void createRandomPersonTest() throws IOException, ParseException {
        Person randomPerson = personFactory.createRandomPerson();
        assertNotNull(randomPerson);
        assertTrue(randomPerson.getFirstName().length() > 1);
        assertTrue(randomPerson.getLastName().length() > 1);
        assertTrue(randomPerson.getEmail().contains(randomPerson.getFirstName().toLowerCase()));
        assertTrue(randomPerson.getEmail().contains(randomPerson.getLastName().toLowerCase()));
        assertTrue(randomPerson.getAge() >= 14);
        assertTrue(randomPerson.getAge() <= 90);
        assertTrue(randomPerson.getStreetAddress().length() > 1);
        assertTrue(randomPerson.getState().length() > 1);
        assertTrue(randomPerson.getState().length() > 1);
        assertTrue(randomPerson.getZipCode().length() > 1);
    }

}