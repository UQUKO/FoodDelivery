package com.example.task;

import com.example.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledData {

    @Autowired
    private GetData getData;

    @Scheduled(cron = "${scheduling.cron.expression}")
    public void execute() {
        getData.readXML();
    }
}
