package com.example.randompersonapi;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class AddressController {

    private RandomPersonFactory personFactory;

    @GetMapping("/address")
    public Address address() throws IOException, ParseException {
        return personFactory.createRandomAddress();
    }
}
