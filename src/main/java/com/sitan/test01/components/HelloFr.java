/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.components;

import org.springframework.stereotype.Component;

/**
 *
 * @author sidaty
 */
@Component
public class HelloFr implements HelloWorld {

    @Override
    public String getLangage() {
        return "FR";
    }

    @Override
    public String sayHello(String name) {
        return "Bonjour " + name;
    }

}
