package com.java.src.lululu;

import com.java.src.lululu.config.Swagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({Swagger2.class})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.java.src.lululu"})
public class LululuApplication {

  public static void main(String[] args) {
    SpringApplication.run(LululuApplication.class, args);
  }

}
