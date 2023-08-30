package com.chenggongdu.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BootUtilsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootUtilsDemoApplication.class);
        log.info("""
                =========================
                BootUtilsDemoApplication is running!
                ==========================
                swagger: http://localhost:8080/doc.html
                """);
    }
}
