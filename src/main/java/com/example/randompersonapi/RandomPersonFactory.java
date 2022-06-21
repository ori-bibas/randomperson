package com.example.randompersonapi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

@Service
public class RandomPersonFactory {

    private static Random rand = new Random();

    public Person createRandomPerson() {


        Person obbs = new Person("Ori", "Bibas", 22, "ob@gmail.com", "4436149427", "10700 London St", "Hollywood", "FL", 32817);
        return obbs;
    }

    // createEmail() returns a string with the users email in the format example: john.doe@gmail.com. Domain and middle char is randomized.
    public static String createEmail(String firstName, String lastName) {
        String[] domains = {"@gmail.com", "@outlook.com", "@hotmail.com", "@aol.com", "@live.com", "@yahoo.com"};
        Character[] chars = {'.', '-', '_'};
        int randomDomain = rand.nextInt(domains.length);
        int randomCharacter = rand.nextInt(chars.length);
        return firstName.toLowerCase() + chars[randomCharacter] + lastName.toLowerCase() + domains[randomDomain];
    }

}
