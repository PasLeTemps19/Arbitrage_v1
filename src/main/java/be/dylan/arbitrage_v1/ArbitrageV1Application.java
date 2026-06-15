package be.dylan.arbitrage_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArbitrageV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ArbitrageV1Application.class, args);
    }

}
