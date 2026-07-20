package com.darshan.dailyprogress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyprogressApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyprogressApplication.class, args);
    }
}