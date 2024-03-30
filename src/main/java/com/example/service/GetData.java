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

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Value("#{'${listOfStations}'.split(',')}")
    private List<String> cityList;

    public void readXML() throws CustomError {
        try {
            // Fetch data from the URL
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);

            NodeList stations = doc.getElementsByTagName("station");
            for (int i = 0; i < stations.getLength(); i++) {
                Element stationElement = (Element) stations.item(i);
                NodeList nameNodeList = stationElement.getElementsByTagName("name");
                if (nameNodeList.getLength() > 0) {
                    String stationName = nameNodeList.item(0).getTextContent();
                    if (cityList.contains(stationName)) {
                        insertDataIntoDatabase(stations.item(i).getTextContent());
                    }
                }
            }

            System.out.println("Data insertion completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomError("Error fetching from URL and writing to the database");
        }
    }

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

    private static String[] sortUseful(String data) {
        String[] andmed = data.strip().split("\n");
        return new String[] {
                andmed[0].strip(),
                andmed[1].strip(),
                andmed[4].strip(),
                andmed[9].strip(),
                andmed[11].strip()
        };
    }
}
