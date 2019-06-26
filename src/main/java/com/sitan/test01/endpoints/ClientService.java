/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sidaty
 */
@Service
public class ClientService {

    @Async
    public void tasks() {
        try {
            Thread.sleep(10_000);
        } catch (Exception e) {
        }
        System.out.println("Task ended");
    }

}
