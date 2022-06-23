package com.example.randompersonapi;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PersonController {

    @Autowired
    private RandomPersonFactory personFactory;

    @GetMapping("/person")
    public Person person () throws IOException, ParseException { return personFactory.createRandomPerson(); }
}
