package com.example.randompersonapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private PersonService personService = new PersonService();

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

}