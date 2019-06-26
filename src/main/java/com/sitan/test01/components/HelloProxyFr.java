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
public class HelloProxyFr implements HelloWorld{
    
    private final HelloFr helloFr = new HelloFr();

    @Override
    public String getLangage() {
        System.out.println("getLangage proxy Befor");
        String lg = helloFr.getLangage();
        System.out.println("getLangage proxy After");
        return lg;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("sayHello proxy Befor");
        String hello = helloFr.sayHello(name);
        System.out.println("sayHello proxy After");
        return hello;
    }
    
    
}
