package com.example.randompersonapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

@Service
public class RandomPersonFactory {

    private static Random rand = new Random();
    private static JSONParser jsonParser = new JSONParser();

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
        String randomFirst = (String) firstNames.get(randomFirstIndex);
        String randomLast = (String) lastNames.get(randomLastIndex);

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
        JSONArray arr = (JSONArray) object.get(arrayName);
        return arr;
    }

}
