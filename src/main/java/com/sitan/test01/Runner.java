/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01;

import com.sitan.test01.components.HelloWorld;
import com.sitan.test01.components.Personne;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author sidaty
 */
@Slf4j
@Component
public class Runner implements CommandLineRunner {

//    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    @Value("${test.nom:Toto}")
    private String nom;

    @Autowired
    private List<HelloWorld> hellos;

    @Override
    public void run(String... args) throws Exception {
        
        try {
            hellos.forEach(hello -> log.info(hello.sayHello(nom)));
            
//            for (HelloWorld helloWorld : hellos) {
//                log.debug(helloWorld.sayHello(nom));
//            }
            
//            throw new RuntimeException("Rien");
        } catch (Exception e) {
            log.error("Erreur bla bla bla", e);
        }
    }

}
