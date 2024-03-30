# FoodDelivery
For Fujitsu Internship


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
