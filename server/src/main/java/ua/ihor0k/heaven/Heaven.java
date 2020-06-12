package ua.ihor0k.heaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ua.ihor0k.heaven")
public class Heaven {
    public static void main(String[] args) {
        SpringApplication.run(Heaven.class, args);
    }
}
