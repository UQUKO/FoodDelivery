package com.example.service;

import com.example.database.WeatherData;
import com.example.database.WeatherDataRepository;
import com.example.task.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetData {

    private WeatherDataRepository weatherDataRepository;

    @Autowired
    public void setWeatherDataRepository(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @Value("#{'${listOfStations}'.split(',')}")
    List<String> cityList;

    /**
     * Reads XML data from a remote URL and inserts relevant station data into the database.
     *
     * This method fetches XML data from a specific URL containing weather observations.
     * It parses the XML document, extracts station information, and inserts relevant data into the database.
     * Only stations whose names are present in the configured city list are processed and inserted into the database.
     *
     * @throws CustomError If an error occurs while fetching data from the URL or writing to the database.
     */
    public void readXML() throws CustomError {
        try {
            // Fetch data from the URL
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            // Parses the data
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);

            // Finds the relevant stations and calls a helper method to insert the data into the database
            NodeList stations = doc.getElementsByTagName("station"); //gets all the stations
            for (int i = 0; i < stations.getLength(); i++) {
                Element stationElement = (Element) stations.item(i);
                NodeList nameNodeList = stationElement.getElementsByTagName("name");
                if (nameNodeList.getLength() > 0) {
                    String stationName = nameNodeList.item(0).getTextContent();// gets the station name
                    if (cityList.contains(stationName)) {
                        insertDataIntoDatabase(stationElement.getTextContent()); // If the name is present,it gets inserted into the database
                    }
                }
            }
            System.out.println("Data insertion completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomError("Error fetching from URL or writing to the database");
        }
    }

    /**
     * Helper method for writing to the database
     * @param data All of the data of one station
     * @throws CustomError If an error occurs while writing to the database
     */
    private void insertDataIntoDatabase(String data) throws CustomError {
        try {
            String[] sortedData = sortUseful(data);

            WeatherData weatherData = new WeatherData();
            weatherData.setObservationTime(LocalDateTime.now());
            weatherData.setStation(sortedData[0]);
            weatherData.setWmocode(Integer.parseInt(sortedData[1]));
            weatherData.setPhenomenon(sortedData[2]);
            weatherData.setAirTemperature(Double.parseDouble(sortedData[3]));
            weatherData.setWindSpeed(Double.parseDouble(sortedData[4]));

            weatherDataRepository.save(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomError("Error writing to the database");
        }
    }

    /**
     * Filters out relevant parameters of the data
     * @param data All of the data of one station
     * @return Array of Strings containing only relevant information
     */
    private static String[] sortUseful(String data) {
        String[] andmed = data.strip().split("\n");
        return new String[] {
                andmed[0].strip(), // name of the station
                andmed[1].strip(), // wmocode
                andmed[4].strip(), // phenomenon
                andmed[9].strip(), // air temperature
                andmed[11].strip() // wind speed
        };
    }
}
