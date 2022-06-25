package com.example.randompersonapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

@Service
public class RandomPersonFactory {

    private static final Random rand = new Random();
    private static final JSONParser jsonParser = new JSONParser();
    private static final String firstNamesURL = "https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/first-names.json";
    private static final String lastNamesURL = "https://raw.githubusercontent.com/ori-bibas/list-of-names/main/src/last-names.json";
    private static final String emailsURL = "https://raw.githubusercontent.com/EthanRBrown/rrad/master/addresses-us-100.json";

    /*
        Written By: Ori Bibas
            This function will return a Person object with randomized attributes.
    */
    public Person createRandomPerson() throws IOException, ParseException {

        Person randomPerson = new Person();

        // Get two JSON Objects from remote repos.
        JSONObject firstNamesObj = getJSONObjectFromRemote(firstNamesURL);
        JSONObject lastNamesObj = getJSONObjectFromRemote(lastNamesURL);

        // Create JSON Arrays for each.
        JSONArray firstNames = (JSONArray) firstNamesObj.get("firstNames");
        JSONArray lastNames = (JSONArray) lastNamesObj.get("lastNames");

        // Get random indexes to pull from.
        int randomFirstIndex = rand.nextInt(firstNames.size());
        int randomLastIndex = rand.nextInt(lastNames.size());

        // Set first and last name to be a random element from their respective arrays.
        String randomFirstName = (String) firstNames.get(randomFirstIndex);
        String randomLastName = (String) lastNames.get(randomLastIndex);

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
            retrieves the object from a random element in that array, then populats an Address object with that random data.
    */
    public Address createRandomAddress() throws IOException, ParseException {

        Address address = new Address();

        JSONObject addresses = getJSONObjectFromRemote(emailsURL);
        JSONArray addressesArray = (JSONArray) addresses.get("addresses");

        // Choose a random object from the addresses array
        int randomIndex = rand.nextInt(addressesArray.size());
        JSONObject obj = (JSONObject) addressesArray.get(randomIndex);

        // Set the properties of that object to the random address, and return.
        address.setStreetAddress((String) obj.get("address1"));
        address.setCity((String) obj.get("city"));
        address.setState((String) obj.get("state"));
        address.setZipCode((String) obj.get("postalCode"));

        return address;
    }

    /*
        Written By: Ori Bibas
            Opens a connection to my GitHub repo with plenty of data, retrieves the JSON data, parses it,
            and returns the JSON object.
    */
    public JSONObject getJSONObjectFromRemote(String link) throws IOException, ParseException {

        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Read in the response to StringBuffer
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the response, and set a JSONArray to the array "addresses"
        JSONObject obj = (JSONObject) jsonParser.parse(response.toString());

        return obj;
    }
}
