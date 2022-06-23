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

    public Person() {}

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

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) {this.state = state; }
    public void setZipCode(int zipCode) { this.zipCode = zipCode; }
}
