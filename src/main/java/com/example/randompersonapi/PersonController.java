package com.example.randompersonapi;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private RandomPersonFactory personFactory;

    @GetMapping("/person")
    public ResponseEntity<Person> person () throws IOException, ParseException {
        Person person = personFactory.createRandomPerson();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(value = "/person/{count}")
    public ArrayList<Person> getPeople(@PathVariable("count") int count) throws IOException, ParseException {
        ArrayList<Person> people = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            people.add(personFactory.createRandomPerson());
        }
        return people;
    }
}
