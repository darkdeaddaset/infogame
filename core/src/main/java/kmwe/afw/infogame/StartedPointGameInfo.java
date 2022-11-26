package kmwe.afw.infogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "kmwe.afw")
public class StartedPointGameInfo {
    public static void main(String[] args) {
        SpringApplication.run(StartedPointGameInfo.class, args);
    }
}