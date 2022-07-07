package com.example.randompersonapi;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class AddressController {

    @Autowired
    private RandomPersonFactory personFactory;

    @GetMapping("/address")
    public Address address() throws IOException, ParseException {
        return personFactory.createRandomAddress();
    }

    @GetMapping("/address/{count}")
    public ArrayList<Address> addresses(@PathVariable("count") int count) throws IOException, ParseException {
        ArrayList<Address> addresses = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            addresses.add(personFactory.createRandomAddress());
        }
        return addresses;
    }

}
