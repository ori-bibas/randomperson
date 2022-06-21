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

    public Person createRandomPerson() {


        Person obbs = new Person("Ori", "Bibas", 22, "ob@gmail.com", "4436149427", "10700 London St", "Hollywood", "FL", 32817);
        return obbs;
    }

}
