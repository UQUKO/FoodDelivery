package com.example.service;
import com.example.database.WeatherData;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class GetData {

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

            Connection conn = DriverManager.getConnection("jdbc:h2:./../weatherdata", "admin", "ilm");
            WeatherData.retrieveAndPrintData(conn);
            System.out.println("Data insertion completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertDataIntoDatabase(String data) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./../weatherdata", "admin", "ilm")) {
            String[] sortAndmed = sorteeriVajalikud(data);

            String query = "INSERT INTO weatherdata (observation_time, station, wmocode, phenomenon, airtemperature, windspeed) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setObject(1, LocalDateTime.now());
                for (int i = 0; i < sortAndmed.length; i++) {
                    preparedStatement.setString(i + 2, sortAndmed[i]);
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
