package com.idf.sipmlerabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleRabbitApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimpleRabbitApplication.class, args);
  }
}
