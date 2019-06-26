/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.repositories;

import com.sitan.test01.endpoints.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sidaty
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
