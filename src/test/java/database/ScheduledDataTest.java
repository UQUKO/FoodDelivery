package database;
import com.example.task.ScheduledData;
import com.example.database.WeatherData;
import org.junit.jupiter.api.Test;

class ScheduledDataTest {

    @Test
    public void testScheduledTask() {
        // Create an instance of ScheduledData
        ScheduledData scheduledData = new ScheduledData();

        // Manually trigger the scheduled task
        scheduledData.execute();

//        WeatherData.main(new String[0]);
    }
}