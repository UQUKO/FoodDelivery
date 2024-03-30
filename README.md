# FoodDelivery
Sub-functionality of the food delivery application, which
calculates the delivery fee for food couriers based on regional base fee, vehicle type, and weather
conditions. The application has a **home page at localhost:8080** for easy and visual access to the calculating
functionality of the application. Database is accessible at **localhost:8080/h2-ui**.

## Business Rules
### Business rules to calculate regional base fee (RBF):
* In case City = Tallinn and:
  * Vehicle type = Car, then RBF = 4 €
  * Vehicle type = Scooter, then RBF = 3,5 €
  * Vehicle type = Bike, then RBF = 3 €
* In case City = Tartu and:
  * Vehicle type = Car, then RBF = 3,5 €
  * Vehicle type = Scooter, then RBF = 3 €
  * Vehicle type = Bike, then RBF = 2,5 €
* In case City = Pärnu and:
  * Vehicle type = Car, then RBF = 3 €
  * Vehicle type = Scooter, then RBF = 2,5 €
  * Vehicle type = Bike, then RBF = 2 €
### Business rules to calculate extra fees for weather conditions:
* Extra fee based on air temperature (**ATEF**) in a specific city is paid in case Vehicle type =
  Scooter or Bike and:
  * Air temperature is less than -10°C, then ATEF = 1 €
  * Air temperature is between -10°C and 0°C, then ATEF = 0,5 € 
* Extra fee based on wind speed (**WSEF**) in a specific city is paid in case Vehicle type = Bike
  and:
  * Wind speed is between 10 m/s and 20 m/s, then WSEF = 0,5 €
  * In case of wind speed is greater than 20 m/s, then the error message “Usage of selected vehicle
  type is forbidden” has to be given
* Extra fee based on weather phenomenon (**WPEF**) in a specific city is paid in case Vehicle
  type = Scooter or Bike and:
  * Weather phenomenon is related to snow or sleet, then WPEF = 1 €
  * Weather phenomenon is related to rain, then WPEF = 0,5 €
  * In case the weather phenomenon is glaze, hail, or thunder, then the error message “Usage of
  selected vehicle type is forbidden” has to be given


# Delivery Controller API Documentation
The Delivery Controller provides an API for calculating delivery fees based on the city and vehicle type.  

## Endpoints
### Calculate Delivery Fee
* URL: /delivery/fee
* Method: GET
* Parameters:
  * **city** (string, required): The name of the city for delivery (e.g., "Tartu", "Tallinn", "Pärnu").
  * **vehicleType** (string, required): The type of vehicle for delivery. (e.g "Bike", "Scooter", "Car")
* Response:
  * Success: Returns the calculated delivery fee.
  * Error: Returns an error message if there's an issue with the weather data for the specified city.
* Example:
```
http
GET /delivery/fee?city=Tartu&vehicleType=Car
  
json
  {
"message": "Fee calculated for city: Tartu, vehicle type: Car\nThe total fee is: 3.5"
  }
 ```
## Usage
1. Make a GET request to /delivery/fee with the required parameters (city and vehicleType) to calculate the delivery fee.
2. Ensure that the city parameter is one of "Tartu", "Tallinn", or "Pärnu".
3. Ensure that the vehicle type parameter is one of "Bike", "Scooter", or "Car"
4. Review the returned response for the calculated fee.
## Error Handling
* If there's an issue with the weather data for the specified city, an error message will be returned.
* Error messages are descriptive and provide information about the encountered issue.
## Dependencies
* WeatherDataService: Provides weather data for calculating delivery fees based on city.
* Linn: Represents a city for delivery.
* Calculator: Performs the calculation of delivery fees based on city and vehicle type.

# Home Controller Documentation
The Home Controller is responsible for handling requests related to the home page.

## Endpoints
### Home Page
* URL: /
* Method: GET
* Description: Retrieves the home page.
* Response: Returns the index.html page.
## Usage
Make a GET request to / to access the home page.
The controller will return the index.html page.
## Dependencies
Spring Framework: Provides the @Controller and @GetMapping annotations for defining controller endpoints.
