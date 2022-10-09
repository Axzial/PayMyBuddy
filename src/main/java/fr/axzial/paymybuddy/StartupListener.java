package fr.axzial.paymybuddy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        String port = env.getProperty("server.port");
        log.info("Running Server On Port : " + port);
    }

}
