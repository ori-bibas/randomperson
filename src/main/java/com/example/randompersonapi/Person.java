package com.example.randompersonapi;

public class Person {

    // Person object will contain:
    // First name, Last name, Age, Email, Phone #, Address (Street, City, State, Zip)
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;

    // Constructor
    public Person(String firstName, String lastName, int age, String email, String phoneNumber, String streetAddress, String city, String state, int zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getStreetAddress() { return streetAddress; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public int getZipCode() { return zipCode; }

}
