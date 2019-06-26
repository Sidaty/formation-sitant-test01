/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 *
 * @author sidaty
 */
@Data
@Embeddable
public class Adresse implements Serializable {
    
    private String region;
    private String cercle;
    private String commune;
    private String localite;
    private String rue;
    private String porte;
}
