/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.components;

import java.util.Date;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author sidaty
 */
@Configuration
public class HelloConfig {

    @Bean
    public HelloWorld helloEs() {
        
        return new HelloWorld() {
            @Override
            public String getLangage() {
                return "ES";
            }

            @Override
            public String sayHello(String name) {
                return "Holla " + name;
            }
        };

    }

    @Bean
    public AuditorAware auditorAware() {
        return () -> Optional.of("test");
    }

    @Scheduled(cron = "${cron.email}")
//    @Scheduled(fixedDelay = 2_000, initialDelay = 10_000)
    public void testScheduler1() {
        System.out.println("Schedule : " + new Date());
    }

}
