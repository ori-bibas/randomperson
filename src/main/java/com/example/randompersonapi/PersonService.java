package com.example.randompersonapi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Random;

@Service
public class PersonService {

    @Autowired
    private PersonJsonService personJsonService;

    private static final Random rand = new Random();

    /*
        Written By: Ori Bibas
            This function will return a Person object with randomized attributes.
    */
    public Person createRandomPerson() throws IOException, ParseException {

        Person randomPerson = new Person();

        // Get random indexes to pull from.
        int randomFirstIndex = rand.nextInt(personJsonService.getFirstNames().size());
        int randomLastIndex = rand.nextInt(personJsonService.getLastNames().size());

        // Set first and last name to be a random element from their respective arrays.
        String randomFirstName = (String) personJsonService.getFirstNames().get(randomFirstIndex);
        String randomLastName = (String) personJsonService.getLastNames().get(randomLastIndex);

        // Setting random email, age between 14-90, phone number, and address.
        String randomEmail = createEmail(randomFirstName, randomLastName);
        int randomAge = rand.nextInt(90 - 14) + 14;
        String randomPhoneNumber = generateRandomPhoneNumber();
        Address address = createRandomAddress();

        // Populate the randomPerson object with all the data generated.
        randomPerson.setFirstName(randomFirstName);
        randomPerson.setLastName(randomLastName);
        randomPerson.setEmail(randomEmail);
        randomPerson.setAge(randomAge);
        randomPerson.setPhoneNumber(randomPhoneNumber);
        randomPerson.setStreetAddress(address.streetAddress);
        randomPerson.setCity(address.city);
        randomPerson.setState(address.state);
        randomPerson.setZipCode(address.zipCode);

        return randomPerson;
    }

    /*
        Written By: Ori Bibas
        Takes in a first name and a last name as a string. Creates randomized email address string, with a random
        domain and character in the middle. Ex: john_doe@hotmail.com
    */
    public String createEmail(String firstName, String lastName) {
        String[] domains = {"@gmail.com", "@outlook.com", "@hotmail.com", "@aol.com", "@live.com", "@yahoo.com"};
        Character[] chars = {'.', '-', '_'};
        int randomDomain = rand.nextInt(domains.length);
        int randomCharacter = rand.nextInt(chars.length);
        return firstName.toLowerCase() + chars[randomCharacter] + lastName.toLowerCase() + domains[randomDomain];
    }

    /*
        Written By: Ori Bibas
            A randomized 10-digit phone number as a string.
    */
    public String generateRandomPhoneNumber() {

        String firstNumber = "", rest = "";

        // For the first number, only digits that are between 2-9
        // Random number format: (rand.nextInt(max - min + 1) + min)     min <= number <= max
        firstNumber += (rand.nextInt(9 - 2 + 1) + 2);

        // For the rest, numbers between 0-9
        for(int i = 0; i < 9; i++) {
            rest += rand.nextInt(10);
        }

        // Concatenate the two strings and return
        return firstNumber + rest;
    }

    /*
        Written By: Ori Bibas
            Creates a new address. Gets JSON object from addresses repository, creates an array from that object,
            retrieves the object from a random element in that array, then populates an Address object with that random data.
    */
    public Address createRandomAddress() throws IOException, ParseException {

        Address address = new Address();

        // Choose a random object from the addresses array
        int randomIndex = rand.nextInt(personJsonService.getAddresses().size());
        JSONObject obj = (JSONObject) personJsonService.getAddresses().get(randomIndex);

        // Set the properties of that object to the random address, and return.
        address.setStreetAddress((String) obj.get("address1"));
        address.setCity((String) obj.get("city"));
        address.setState((String) obj.get("state"));
        address.setZipCode((String) obj.get("postalCode"));

        return address;
    }
}
