/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.components;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *
 * @author sidaty
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        double test = Math.random();
        return test > 0.5 ? Health.up().withDetail("Value", test).build() : Health.down().withDetail("Value", test).build();
    }

}
