/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sidaty
 */
@Slf4j
@RestController
@RequestMapping("emails")
public class EmailEndpoint {

    private final EmailService emailService;

    public EmailEndpoint(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("send")
    public String send(@RequestParam("to") String to, @RequestParam("s") String subject, @RequestParam("c") String content) {
        try {
            emailService.send(to, subject, content);

            return "OK";
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            return "KO";
        }
    }

}
