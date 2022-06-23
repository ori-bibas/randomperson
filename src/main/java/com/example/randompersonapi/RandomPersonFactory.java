package com.example.randompersonapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

@Service
public class RandomPersonFactory {

    private static Random rand = new Random();
    private static JSONParser jsonParser = new JSONParser();
    private static RestTemplate restTemplate = new RestTemplate();

    /*
        Written By: Ori Bibas
        This function will return a Person object with randomized attributes.
    */
    public Person createRandomPerson() throws IOException, ParseException {

        Person randomPerson = new Person();

        // Set path names to the JSON files.
        String firstNamesPath = pathToJSON("first-names.json");
        String lastNamesPath = pathToJSON("last-names.json");

        // Get two JSON arrays, one for the first names one for the last names.
        JSONArray firstNames = getJSONArray(firstNamesPath, "firstNames");
        JSONArray lastNames = getJSONArray(lastNamesPath, "lastNames");

        // Get random indexes.
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

        randomPerson.setFirstName(randomFirstName);
        randomPerson.setLastName(randomLastName);
        randomPerson.setEmail(randomEmail);
        randomPerson.setAge(randomAge);
        randomPerson.setPhoneNumber(randomPhoneNumber);
        randomPerson.setStreetAddress(address.streetAddress);
        randomPerson.setCity(address.city);
        randomPerson.setState(address.state);
        randomPerson.setZipCode(address.zipCode);

        // Finally, return the person with randomized attributes.
        return randomPerson;
    }

    /*
        Written By: Ori Bibas
        Arguments: First name and a last name as a string.
        Return: A randomized email address string, with a random domain and character in the middle. Ex: john_doe@hotmail.com
    */
    public static String createEmail(String firstName, String lastName) {
        String[] domains = {"@gmail.com", "@outlook.com", "@hotmail.com", "@aol.com", "@live.com", "@yahoo.com"};
        Character[] chars = {'.', '-', '_'};
        int randomDomain = rand.nextInt(domains.length);
        int randomCharacter = rand.nextInt(chars.length);
        return firstName.toLowerCase() + chars[randomCharacter] + lastName.toLowerCase() + domains[randomDomain];
    }

    /*
        Written By: Ori Bibas
        Arguments: Pass a string with the name of the JSON file.
        Return: A string that is the path to that file in the resources folder.
    */
    public static String pathToJSON(String jsonFileName) {
        return "src/main/resources/" + jsonFileName;
    }

    /*
        Written By: Ori Bibas
        Arguments: Pass the full path to the JSON file as a string, pass the string name of the array inside the JSON object you want.
        Return Type: JSONArray
    */
    public static JSONArray getJSONArray(String pathName, String arrayName) throws IOException, ParseException {
        JSONObject object = (JSONObject) jsonParser.parse(new FileReader(pathName));
        return (JSONArray) object.get(arrayName);
    }

    /*
        Written By: Ori Bibas
        Return: A randomized 10-digit phone number as a string.
    */
    public static String generateRandomPhoneNumber() {

        String firstNumber = "", rest = "";

        // For the area code, only digits that are between 2-9
        firstNumber += rand.nextInt((10 - 2) + 2);

        // For the rest, numbers between 0-9
        for(int i = 0; i < 9; i++) {
            rest += rand.nextInt(10);
        }

        // Concatenate the two strings and return
        return firstNumber + rest;
    }

    /*
        Written By: Ori Bibas
            Opens a connection to a public GitHub repository with a lot of addresses, retrieves the JSON data, parses it,
            picks a random element, and populates an address object with street address, city, state, and zip code.
        Return Type: Address
    */
    public static Address createRandomAddress() throws IOException, ParseException {

        // Open a connection to the JSON data on GitHub
        Address address = new Address();
        URL url = new URL("https://raw.githubusercontent.com/EthanRBrown/rrad/master/addresses-us-100.json");
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
        JSONObject addressesObject = (JSONObject) jsonParser.parse(response.toString());
        JSONArray addressesArray = (JSONArray) addressesObject.get("addresses");

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

}
