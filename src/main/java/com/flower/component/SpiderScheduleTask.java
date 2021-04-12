package com.flower.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpiderScheduleTask {
    @Autowired
    private Spider spider;


    @Scheduled(fixedRate = 1000*60*60)
    public void spiderTasks() throws IOException {
        String url = "https://www.aihuaju.com/search-20-0-0-0-1.html";
        spider.spiderNewFlower(url);
    }
}
