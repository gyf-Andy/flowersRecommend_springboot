package com.flower.component;

import com.flower.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动运行
 */
@Component
//change
//@AnnotationAwareOrderComparator(value = 1)
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private Spider spider;
    @Autowired
    private FlowerService flowerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (flowerService.count() == 0) {
            for (int i = 25; i > 0; i--) {
                String url = "https://www.aihuaju.com/search-20-0-0-0-" + i + ".html";
                try {
                    spider.spiderAllFlower(url);
                } catch (Exception e) {
                    Thread.currentThread().sleep(3000);
                    spider.spiderAllFlower(url);
                }
                Thread.currentThread().sleep(3000);
            }
        }
    }
}
