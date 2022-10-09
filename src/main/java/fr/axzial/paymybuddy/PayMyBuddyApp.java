package fr.axzial.paymybuddy;

import fr.axzial.paymybuddy.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
@Slf4j
public class PayMyBuddyApp {

    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApp.class, args);
    }

}
