package com.example.randompersonapi;

import org.junit.jupiter.api.Test;

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
}