/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import com.sitan.test01.components.HelloWorld;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sidaty
 */
@RestController
@RequestMapping("hello")
public class HelloEndpoint {

    @Value("${test.nom:Toto}")
    private String nom;

    @Value("${langage.default:EN}")
    private String defaultLangage;

    @Autowired
    private List<HelloWorld> helloWorlds;

    @RequestMapping("say")
    public String hello(@RequestParam(value = "lg", required = false) String langage, @RequestParam String name) {
        
        langage = Optional.ofNullable(langage).orElse(defaultLangage);
        
        for (HelloWorld helloWorld : helloWorlds) {
            if (langage.equals(helloWorld.getLangage())) {
                return helloWorld.sayHello(name);
            }
        }
        
        return helloWorlds.get(0).sayHello(name);
    }

}
