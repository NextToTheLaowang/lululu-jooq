package com.java.src.lululu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LululuApplication {

  public static void main(String[] args) {
    SpringApplication.run(LululuApplication.class, args);
  }

}
