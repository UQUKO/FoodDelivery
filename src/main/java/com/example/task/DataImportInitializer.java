package com.example.task;

import com.example.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Initializes data import process.
 * <p>
 * This component initializes the data import process by calling the {@link #readXML()} method
 * of the {@link GetData} bean during the post-construction phase.
 * </p>
 */
@Component
public class DataImportInitializer {

    private final GetData getData;

    /**
     * Constructs a new DataImportInitializer with the specified GetData bean.
     *
     * @param getData The GetData bean used for importing data.
     */
    @Autowired
    public DataImportInitializer(GetData getData) {
        this.getData = getData;
    }

    /**
     * Initializes the data import process by reading XML data.
     * <p>
     * This method is annotated with {@link PostConstruct} to indicate that it should be invoked
     * after the bean has been constructed and dependencies have been injected.
     * </p>
     * <p>
     * It calls the {@link GetData#readXML()} method to read XML data from a remote URL
     * and insert relevant station data into the database.
     * </p>
     * <p>
     * If an error occurs during the data import process, it throws a {@link CustomError}.
     * </p>
     *
     * @throws CustomError If an error occurs during the data import process.
     */
    @PostConstruct
    public void init() throws CustomError {
        getData.readXML();
    }
}
