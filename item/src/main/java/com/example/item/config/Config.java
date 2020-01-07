package com.example.item.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;



@Configuration
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class.getName());



    @Bean
    public ExitCodeExceptionMapper exitCodeToExceptionMapper() {

        return exception -> {
            if (exception.getCause() instanceof NumberFormatException) {
                return 80;
            }
            return 1;
        };
    }

    @Bean
    public DemoListener demoListenerBean() {

        return new DemoListener();
    }

    private static class DemoListener {

        @EventListener
        public void exitEvent(ExitCodeEvent event) {
            logger.warn("Exit code: " + event.getExitCode());
        }
    }

}
