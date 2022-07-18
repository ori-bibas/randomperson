package com.example.randompersonapi;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    PersonJsonService personJsonService = new PersonJsonService();
    private PersonService personService = new PersonService();

    PersonServiceTest() throws IOException, ParseException {}

    @Test
    public void testCreateEmail() {

        // when
        String firstName = "Ori";
        String lastName = "Bibas";

        // then
        String result = personService.createEmail(firstName, lastName);

        // should
        assertTrue(result.contains(firstName.toLowerCase()));
        assertTrue(result.contains(lastName.toLowerCase()));
        assertTrue(result.contains(".com"));
        assertTrue(result.contains("@"));

        boolean containsChar;
        if(result.contains("-") || result.contains(".") || result.contains("_")) {
            containsChar = true;
        }
        else {
            containsChar = false;
        }

        assertTrue(containsChar);
    }

    @Test
    public void generateRandomPhoneNumberTest() {

        String phoneNumber = personService.generateRandomPhoneNumber();

        assertTrue(phoneNumber.length() == 10);
        assertTrue(phoneNumber.charAt(0) != '0');
        assertTrue(phoneNumber.charAt(0) != '1');
    }

    @Test
    public void createRandomAddressTest() throws IOException, ParseException {

        Address address = personService.createRandomAddress();

        assertNotNull(address);
        assertFalse(address.streetAddress.isEmpty());
        assertFalse(address.city.isEmpty());
        assertFalse(address.state.isEmpty());
        assertFalse(address.zipCode.isEmpty());
    }

    @Test
    public void createRandomPersonTest() throws IOException, ParseException {

        Person person = personService.createRandomPerson();

        assertNotNull(person);

        int age = person.getAge();
        assertTrue(age >= 14 && age <= 90);
    }
}