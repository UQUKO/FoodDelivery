package com.example.task;

import com.example.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Executes scheduled data import task.
 * <p>
 * This method is annotated with {@link Scheduled} to indicate that it should be executed
 * based on the configured cron expression specified in the application properties.
 * </p>
 */
@Component
public class ScheduledData {

    @Autowired
    private GetData getData;

    /**
     * Executes the scheduled data import task by reading XML data.
     * <p>
     * This method is invoked based on the configured cron expression specified in the application properties.
     * It calls the {@link GetData#readXML()} method to read XML data from a remote URL
     * and insert relevant station data into the database.
     * </p>
     * <p>
     * If an error occurs during the data import process, it throws a {@link CustomError}.
     * </p>
     *
     * @throws CustomError If an error occurs during the data import process.
     */
    @Scheduled(cron = "${scheduling.cron.expression}")
    public void execute() throws CustomError {
        getData.readXML();
    }
}
