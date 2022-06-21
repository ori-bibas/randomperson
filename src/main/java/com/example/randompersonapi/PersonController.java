package com.example.randompersonapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private RandomPersonFactory personFactory;

    @GetMapping("/person")
    public Person person () { return personFactory.createRandomPerson(); }
}
