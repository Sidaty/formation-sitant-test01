package com.sitan.test01;

import com.sitan.test01.components.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Test01Application {

    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }

    @Bean
    public HelloWorld helloHs() {
        return new HelloWorld() {
            @Override
            public String getLangage() {
                return "HS";
            }

            @Override
            public String sayHello(String name) {
                return "Barka " + name;
            }
        };
    }

}
