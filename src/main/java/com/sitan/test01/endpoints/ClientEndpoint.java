/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import com.sitan.test01.repositories.ClientRepository;
import com.sitan.test01.utils.MyException;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sidaty
 */
@Slf4j
@RestController
@RequestMapping("clients")
public class ClientEndpoint {

    @Value("${requete.client.ca}")
    private String requete;

    private final ClientService clientService;
    private final JdbcTemplate jdbcTemplate;
    private final ClientRepository clientRepository;
    private final EmailService emailService;

    public ClientEndpoint(ClientService clientService, 
            JdbcTemplate jdbcTemplate, ClientRepository clientRepository,
            EmailService emailService) {
        this.clientService = clientService;
        this.jdbcTemplate = jdbcTemplate;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    @GetMapping("with-ca")
    public List<Client> findWithCA() {
        RowMapper<Client> clientMapper = (rs, i) -> Client
                .builder()
                .nom(rs.getString("nom"))
                .prenom(rs.getString("prenom"))
                .ca(rs.getDouble("ca"))
                .build();

        List<Client> clients = jdbcTemplate.query(requete, clientMapper);

        return clients;
    }

    @PostMapping
    @Transactional(rollbackFor = {MyException.class})
    public ResponseEntity create(@RequestBody Client client) {
        client = clientRepository.save(client);
//        if (true) {
//            throw new MyException("Error");
//        }

//        emailService.send(requete, requete, requete);
        return ResponseEntity.ok(client);

    }

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("page")
    public Page<Client> findPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @GetMapping("{id}")
    public Client findById(@PathVariable Long id) {
        return clientRepository
                .findById(id)
                .orElse(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("{id}/nom")
    public ResponseEntity getNom(@PathVariable Long id) {
        return clientRepository
                .findById(id)
                .map(Client::getNom)
                .map(String::toUpperCase)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok("Toto"));

//        return clientRepository
//                .findById(id)
//                .map(client -> client.getNom())
//                .map(nom -> ResponseEntity.ok(nom))
//                .orElse(ResponseEntity.ok("Toto"));
    }

    @RequestMapping("tasks")
    public String tasks() {
        clientService.tasks();
        return "OK";
    }
    
}
