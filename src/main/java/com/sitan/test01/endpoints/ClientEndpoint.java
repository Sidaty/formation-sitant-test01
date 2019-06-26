/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import com.sitan.test01.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
@RestController
@RequestMapping("clients")
public class ClientEndpoint {

    @Value("${requete.client.ca}")
    private String requete;

    private final JdbcTemplate jdbcTemplate;
    private final ClientRepository clientRepository;

    public ClientEndpoint(JdbcTemplate jdbcTemplate, ClientRepository clientRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.clientRepository = clientRepository;
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
    public ResponseEntity create(@RequestBody Client client) {
        return ResponseEntity.ok(clientRepository.saveAndFlush(client));
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

}
