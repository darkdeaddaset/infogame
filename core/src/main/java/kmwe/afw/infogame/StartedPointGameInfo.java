package kmwe.afw.infogame;

import kmwe.afw.infogame.properties.ImageStoriesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "kmwe.afw")
@EnableConfigurationProperties({ImageStoriesProperties.class})
public class StartedPointGameInfo {
    public static void main(String[] args) {
        SpringApplication.run(StartedPointGameInfo.class, args);
    }
}