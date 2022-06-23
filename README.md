# Random Person API
## REST API created with Java + Spring Boot

### About

I've created this API for a user to be able to retrieve a person with randomized attributes. I programmed it in Java, and utilized Spring framework tools for creating the REST API functionality. When calling a GET request to the end-point, it will return a JSON object with a random first name, last name, email address, age, phone number, street address, city, state, and zip code. The app is hosted with Heroku. Please feel free to use this API to test your applications

### How To Use

To call my API, make a GET request to ```https://randomperson-api.herokuapp.com/person```

##### JSON Response format

```json

{
  "firstName": "Ori",
  "lastName": "Bibas",
  "age": 21,
  "email": "oribibas@knights.ucf.edu",
  "phoneNumber": "5555555555",
  "streetAddress": "1600 Pennsylvania Avenue NW",
  "city": "Washington",
  "state": "DC",
  "zipCode": "20500"
}

```

##### Python Example

```python

import requests

URL = "https://randomperson-api.herokuapp.com/person"
response = requests.get(URL)

# To get the JSON data from the response as key-value pairs
json_data = response.json()

# To access individual elements of the JSON data
firstName = json_data.get("firstName")
lastName = json_data.get("lastName")
age = json_data.get("age")
email = json_data.get("email")
phoneNumber = json_data.get("phoneNumber")
streetAddress = json_data.get("streetAddress")
city = json_data.get("city")
state = json_data.get("state")
zip = json_data.get("zipCode")

```
