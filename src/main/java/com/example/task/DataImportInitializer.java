package com.example.task;

import com.example.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataImportInitializer {

    private final GetData getData;

    @Autowired
    public DataImportInitializer(GetData getData) {
        this.getData = getData;
    }

    @PostConstruct
    public void init() throws CustomError {
        getData.readXML();
    }
}
