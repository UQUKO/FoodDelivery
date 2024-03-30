package com.example.service;

import com.example.database.WeatherData;
import com.example.database.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;

@Service
public class GetData {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    public void readXML() {
        try {
            // Fetch data from the URL
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);

            NodeList stations = doc.getElementsByTagName("station");
            String tallinn = stations.item(1).getTextContent();
            insertDataIntoDatabase(tallinn);
            String tartu = stations.item(9).getTextContent();
            insertDataIntoDatabase(tartu);
            String pärnu = stations.item(29).getTextContent();
            insertDataIntoDatabase(pärnu);

            System.out.println("Data insertion completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertDataIntoDatabase(String data) {
        String[] sortAndmed = sorteeriVajalikud(data);

        WeatherData weatherData = new WeatherData();
        weatherData.setObservationTime(LocalDateTime.now());
        weatherData.setStation(sortAndmed[0]);
        weatherData.setWmocode(Integer.parseInt(sortAndmed[1]));
        weatherData.setPhenomenon(sortAndmed[2]);
        weatherData.setAirTemperature(Double.parseDouble(sortAndmed[3]));
        weatherData.setWindSpeed(Double.parseDouble(sortAndmed[4]));

        weatherDataRepository.save(weatherData);
    }

    private static String[] sorteeriVajalikud(String data) {
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
