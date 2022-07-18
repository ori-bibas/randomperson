# Random Person API
### REST API created with Java + Spring Boot to generate fake people with random attributes.

# About

I've created this API for a user to be able to retrieve a person with randomized attributes. I programmed it in Java, and utilized Spring framework tools for creating the REST API functionality. When calling a GET request to the end-point, it will return a JSON object with a random first name, last name, email address, age, phone number, street address, city, state, and zip code. The app is hosted with Heroku. You may also use this to get multiple people, in which case it will return a JSON array. You may also use the end-point to generate random addresses as well. Feel free to use this API to test your applications!

# How To Use This API

To get one person, make a GET request to ```http://localhost:8080/person```

This will return a JSON response that will look like this:

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

To get multiple people, make a GET request to ```http://localhost:8080/person/count``` where count is an integer that represents how many people you'd like to return. This will return a JSON array of people.

For example, to get 2 people, GET ```http://localhost:8080/person/2```. The response will look like:

```json

[
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
  },
  {
    "firstName": "John",
    "lastName": "Doe",
    "age": 57,
    "email": "john_doe@outlook.com",
    "phoneNumber": "5552319704",
    "streetAddress": "451 Main St",
    "city": "New York City",
    "state": "NY",
    "zipCode": "10451"
  }
]

```

You may also use this API to retrieve just addresses. To achieve this, make a GET request to ```http://localhost:8080/address```. You may also do the same thing as above to retrieve multiple addresses. The response will look like this:

```json
{
  "streetAddress": "1600 Pennsylvania Avenue NW",
  "city": "Washingston",
  "state": "DC",
  "zipCode": "20500"
}
```

# Code Examples

### Python Example

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

### JavaScript Example

```javascript

const url = "https://randomperson-api.herokuapp.com/person";

const userAction = async () => {
  const response = await fetch(url);    // Request a response from the URL using fetch
  const myJson = await response.json();    // Extract JSON from the HTTP response
  // Do something with your JSON
}

```






